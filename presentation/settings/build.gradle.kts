plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.settings"
}

dependencies {
    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(projects.domain.systemFeatures)
    implementation(projects.domain.settings)
    implementation(projects.presentation.designSystem.dialogs)
    implementation(projects.presentation.designSystem.dimensions)
    implementation(projects.presentation.designSystem.icons)
    implementation(projects.presentation.designSystem.progressIndicators)
    implementation(projects.presentation.designSystem.shapes)
    implementation(projects.presentation.designSystem.switch)
    implementation(projects.presentation.designSystem.text)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.previews)
    implementation(projects.presentation.utils)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(projects.presentation.paparazzi)
}
