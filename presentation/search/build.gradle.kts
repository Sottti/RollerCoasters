import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.search"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.domain.rollerCoasters))
    implementation(project(Modules.presentation.designSystem.dimensions))
    implementation(project(Modules.presentation.designSystem.progressIndicators))
    implementation(project(Modules.presentation.designSystem.rollerCoasterCard))
    implementation(project(Modules.presentation.designSystem.text))
    implementation(project(Modules.presentation.empty))
    implementation(project(Modules.presentation.error))
    implementation(project(Modules.presentation.topBars))
    ksp(libs.hilt.compiler)
}