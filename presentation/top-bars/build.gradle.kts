import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.top.bars"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.presentation.designSystem.icons))
    implementation(project(Modules.presentation.designSystem.text))
    implementation(project(Modules.presentation.navigation))
}