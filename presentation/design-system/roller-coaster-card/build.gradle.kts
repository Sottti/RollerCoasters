import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.roller.coaster.card"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.presentation.designSystem.colors))
    implementation(project(Modules.presentation.designSystem.dimensions))
    implementation(project(Modules.presentation.designSystem.text))
    implementation(project(Modules.presentation.designSystem.themes))
    implementation(project(Modules.presentation.fixtures))
    implementation(project(Modules.presentation.imageLoading))
    implementation(project(Modules.presentation.previews))
}