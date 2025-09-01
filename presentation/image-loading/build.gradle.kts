import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.image.loading"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    api(project(module.domain.model))
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor3)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(project(module.presentation.designSystem.progressIndicators))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.fixtures))
    implementation(project(module.presentation.previews))
    runtimeOnly(libs.startup.runtime)
    testImplementation(project(module.presentation.tests))
}