import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.tasks.testing.Test
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainService
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.gradle.versions) apply true
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.secrets) apply false
}

val checkModuleDependencyBoundaries = tasks.register("checkModuleDependencyBoundaries") {
    group = "verification"
    description = "Checks direct project dependencies follow the configured module boundary rules."

    doLast {
        val dependencyViolations = allprojects
            .flatMap { project ->
                project.configurations.flatMap { configuration ->
                    configuration.dependencies
                        .withType(ProjectDependency::class.java)
                        .filter { dependency -> dependency.path != project.path }
                        .map { dependency ->
                            ProjectDependencyEdge(
                                configuration = configuration.name,
                                source = project.path,
                                target = dependency.path,
                            )
                        }
                }
            }
            .flatMap { dependency -> dependency.boundaryViolations() }

        val pluginViolations = allprojects
            .filter { project -> project.path in pureKotlinModules }
            .flatMap { project -> project.pureKotlinModuleViolations() }

        val violations = (dependencyViolations + pluginViolations)
            .distinct()
            .sorted()

        if (violations.isNotEmpty()) {
            throw GradleException(
                buildString {
                    appendLine("Module dependency boundary violations found:")
                    violations.forEach { violation -> appendLine("- $violation") }
                },
            )
        }
    }
}

subprojects {
    afterEvaluate {
        when {
            plugins.hasPlugin("com.android.application") -> {
                configure<ApplicationExtension> { androidApplicationConfig() }
            }

            plugins.hasPlugin("com.android.library") -> androidLibraryConfig()
        }
    }

    plugins.withId("com.android.application") { configureKotlinAndroid() }

    plugins.withId("com.android.library") { configureKotlinAndroid() }

    plugins.withId("app.cash.paparazzi") { configurePaparazziTests() }

    tasks.matching { it.name == "check" }.configureEach {
        dependsOn(checkModuleDependencyBoundaries)
    }

    plugins.withId("org.jetbrains.kotlin.jvm") {
        extensions.configure<KotlinJvmProjectExtension> {
            explicitApi()
            jvmToolchain(17)
            compilerOptions {
                freeCompilerArgs.add("-Xcontext-parameters")
            }
        }
    }
}

private data class ProjectDependencyEdge(
    val configuration: String,
    val source: String,
    val target: String,
)

private val dependencyFreeModules = setOf(
    ":domain:locales",
    ":domain:model",
    ":domain:system-features",
    ":presentation:navigation",
    ":presentation:previews",
    ":presentation:string-provider",
)

private val pureKotlinModules = setOf(
    ":domain:fixtures",
    ":domain:locales",
    ":domain:model",
    ":domain:roller-coasters",
    ":domain:settings",
    ":domain:system-features",
    ":presentation:navigation",
    ":presentation:paparazzi",
    ":utils:time-dates",
)

private val pureKotlinForbiddenPluginIds = mapOf(
    "com.android.application" to "Android application",
    "com.android.library" to "Android library",
    "com.google.dagger.hilt.android" to "Hilt Android",
)

private val presentationSupportModules = setOf(
    ":presentation:fixtures",
    ":presentation:paparazzi",
    ":presentation:previews",
)

private fun ProjectDependencyEdge.boundaryViolations(): List<String> = buildList {
    if (source in dependencyFreeModules) {
        add("$source must not declare project dependencies, but $configuration depends on $target.")
    }

    if (target == ":app") {
        add("$source must not depend on :app via $configuration.")
    }

    if (source.startsWith(":domain:") && !target.startsWith(":domain:")) {
        add("$source is a domain module and may only depend on domain modules, but $configuration depends on $target.")
    }

    if (source.startsWith(":data:") && !target.isAllowedDataDependency()) {
        add("$source is a data module and may only depend on data, domain, or utils modules, but $configuration depends on $target.")
    }

    if (source.startsWith(":presentation:") && target.isForbiddenPresentationDependency()) {
        add("$source is a presentation module and must not depend on data, di, or app modules, but $configuration depends on $target.")
    }

    if (source.startsWith(":presentation:design-system:") && target.isForbiddenDesignSystemDependency()) {
        add("$source is a design-system module and must not depend on $target via $configuration.")
    }

    if (source.isNonPresentationLayer() && target in presentationSupportModules) {
        add("$source must not depend on presentation support module $target via $configuration.")
    }

    if (source == ":di" && !target.isAllowedDiDependency()) {
        add("$source may only depend on data or domain modules, but $configuration depends on $target.")
    }
}

private fun Project.pureKotlinModuleViolations(): List<String> = buildList {
    if (!plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
        add("$path is configured as a pure Kotlin module and must apply the Kotlin JVM plugin.")
    }

    pureKotlinForbiddenPluginIds
        .filter { (pluginId, _) -> plugins.hasPlugin(pluginId) }
        .forEach { (pluginId, description) ->
            add("$path is configured as a pure Kotlin module and must not apply the $description plugin ($pluginId).")
        }
}

private fun String.isAllowedDataDependency() =
    startsWith(":data:") || startsWith(":domain:") || startsWith(":utils:")

private fun String.isForbiddenPresentationDependency() =
    startsWith(":data:") || this == ":di" || this == ":app"

private fun String.isForbiddenDesignSystemDependency() =
    this == ":presentation:app-shell" ||
        startsWith(":data:") ||
        this == ":di" ||
        this == ":app"

private fun String.isNonPresentationLayer() =
    startsWith(":domain:") || startsWith(":data:") || this == ":di" || this == ":app"

private fun String.isAllowedDiDependency() =
    startsWith(":data:") || startsWith(":domain:")

private fun Project.configurePaparazziTests() {
    val javaToolchains = extensions.getByType<JavaToolchainService>()
    tasks.withType<Test>().configureEach {
        javaLauncher.set(
            javaToolchains.launcherFor {
                languageVersion.set(JavaLanguageVersion.of(21))
            },
        )
    }
}

private fun ApplicationExtension.androidApplicationConfig() {
    compileSdk = compileSdk()

    defaultConfig {
        minSdk = minSdk()
        targetSdk = targetSdk()
    }

    compileOptions {
        sourceCompatibility = javaVersion()
        targetCompatibility = javaVersion()
    }
}

private fun Project.androidLibraryConfig() {
    extensions.configure<LibraryExtension> {
        compileSdk = compileSdk()
        defaultConfig { minSdk = minSdk() }
        lint { targetSdk = targetSdk() }

        compileOptions {
            sourceCompatibility = javaVersion()
            targetCompatibility = javaVersion()
        }
    }
}

private fun Project.configureKotlinAndroid() {
    extensions.configure<KotlinAndroidProjectExtension> {
        explicitApi()
        jvmToolchain(17)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-Xcontext-parameters")
            freeCompilerArgs.add("-Xannotation-default-target=param-property")
        }
    }
}

private fun compileSdk() = libs.versions.compileSdk.get().toInt()
private fun javaVersion() = JavaVersion.VERSION_17
private fun minSdk() = libs.versions.minSdk.get().toInt()
private fun targetSdk() = libs.versions.targetSdk.get().toInt()
