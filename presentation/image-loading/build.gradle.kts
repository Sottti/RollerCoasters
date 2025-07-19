import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.image.loading"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    api(project(Modules.domain.model))
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor3)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(project(Modules.presentation.designSystem.progressIndicators))
    implementation(project(Modules.presentation.designSystem.themes))
    implementation(project(Modules.presentation.fixtures))
    implementation(project(Modules.presentation.previews))
    runtimeOnly(libs.startup.runtime)
}