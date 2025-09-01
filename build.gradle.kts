import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.dependency.analysis) apply false
    alias(libs.plugins.dependency.versions) apply true
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.secrets) apply false
}

subprojects {
    pluginManager.apply("com.autonomousapps.dependency-analysis")

    afterEvaluate {
        when {
            plugins.hasPlugin("com.android.application") -> {
                configure<ApplicationExtension> { androidApplicationConfig() }
            }

            plugins.hasPlugin("com.android.library") -> androidLibraryConfig()
        }
    }
    extensions.findByType<KotlinAndroidProjectExtension>()?.apply {
        jvmToolchain(17)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.add("-Xwhen-guards")
        }
        explicitApi()
    }
    plugins.withId("org.jetbrains.kotlin.jvm") {
        extensions.configure<KotlinProjectExtension> {
            explicitApi()
            jvmToolchain(17)
        }
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

private fun compileSdk() = libs.versions.compileSdk.get().toInt()
private fun javaVersion() = JavaVersion.VERSION_17
private fun minSdk() = libs.versions.minSdk.get().toInt()
private fun targetSdk() = libs.versions.targetSdk.get().toInt()

@Suppress("unused")
private val kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtension.get()
