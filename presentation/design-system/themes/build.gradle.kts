import com.sottti.roller.coasters.buildSrc.Modules

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
    api(project(Modules.presentation.designSystem.colors))
    implementation(libs.compose.material)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.di))
    implementation(project(Modules.domain.settings))
    implementation(project(Modules.presentation.designSystem.dimensions))
    implementation(project(Modules.presentation.designSystem.typography))
}