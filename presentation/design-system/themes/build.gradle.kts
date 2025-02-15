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
    api(project(module.presentation.designSystem.colors))
    api(project(module.utils.device))
    implementation(libs.compose.material)
    implementation(libs.core)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(platform(libs.compose.bom))
    implementation(project(module.data.settings))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.icons))
    implementation(project(module.presentation.designSystem.typography))
}