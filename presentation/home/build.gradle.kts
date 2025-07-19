import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.home"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.presentation.aboutMe))
    implementation(project(Modules.presentation.designSystem.icons))
    implementation(project(Modules.presentation.designSystem.text))
    implementation(project(Modules.presentation.designSystem.themes))
    implementation(project(Modules.presentation.explore))
    implementation(project(Modules.presentation.favourites))
    implementation(project(Modules.presentation.navigation))
    implementation(project(Modules.presentation.rollerCoasterDetails))
    implementation(project(Modules.presentation.search))
    implementation(project(Modules.presentation.settings))
    ksp(libs.hilt.compiler)
}