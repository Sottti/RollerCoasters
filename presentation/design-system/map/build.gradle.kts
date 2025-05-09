import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.map"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.maps)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.designSystem.themes))
}