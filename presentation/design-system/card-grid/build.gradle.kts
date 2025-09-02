import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.card.grid"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.icons))
    implementation(project(module.presentation.designSystem.text))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.previews))
    testImplementation(project(module.presentation.tests))
}