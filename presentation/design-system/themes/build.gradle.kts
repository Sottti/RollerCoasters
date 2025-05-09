import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.themes"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    api(libs.compose.window.size)
    api(project(module.presentation.designSystem.colors))
    implementation(libs.compose.material)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(platform(libs.compose.bom))
    implementation(project(module.di))
    implementation(project(module.domain.settings))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.icons))
    implementation(project(module.presentation.designSystem.typography))
}