import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.home"
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.aboutMe))
    implementation(project(module.presentation.designSystem.icons))
    implementation(project(module.presentation.designSystem.text))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.explore))
    implementation(project(module.presentation.favourites))
    implementation(project(module.presentation.settings))
    kapt(libs.hilt.compiler)
}