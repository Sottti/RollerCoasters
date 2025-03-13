import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.explore"
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    implementation(platform(libs.compose.bom))
    implementation(project(module.data.rollerCoasters))
    implementation(project(module.presentation.designSystem.chip))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.progressIndicators))
    implementation(project(module.presentation.designSystem.rollerCoasterCard))
    implementation(project(module.presentation.designSystem.text))
    implementation(project(module.presentation.imageLoading))
    implementation(project(module.presentation.topBars))
    implementation(project(module.presentation.utils.format))
    ksp(libs.hilt.compiler)
}