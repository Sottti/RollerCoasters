import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "co.sottti.roller.coasters.presentation.design.system.themes"
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    api(libs.compose.window.size)
    implementation(libs.compose.material)
    implementation(platform(libs.compose.bom))
    implementation(project(module.data.settings))
    implementation(project(module.presentation.designSystem.colors))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.typography))
}