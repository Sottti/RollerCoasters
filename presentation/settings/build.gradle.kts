import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.settings"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.domain.features))
    implementation(project(Modules.domain.settings))
    implementation(project(Modules.presentation.designSystem.dialogs))
    implementation(project(Modules.presentation.designSystem.icons))
    implementation(project(Modules.presentation.designSystem.progressIndicators))
    implementation(project(Modules.presentation.designSystem.switch))
    implementation(project(Modules.presentation.designSystem.text))
    implementation(project(Modules.presentation.designSystem.themes))
    implementation(project(Modules.presentation.previews))
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}