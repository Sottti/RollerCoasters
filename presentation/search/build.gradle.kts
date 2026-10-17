plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.search"
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(projects.domain.fixtures)
    implementation(projects.domain.rollerCoasters)
    implementation(projects.presentation.designSystem.dimensions)
    implementation(projects.presentation.designSystem.empty)
    implementation(projects.presentation.designSystem.error)
    implementation(projects.presentation.designSystem.icons)
    implementation(projects.presentation.designSystem.progressIndicators)
    implementation(projects.presentation.designSystem.rollerCoasterCard)
    implementation(projects.presentation.designSystem.searchBar)
    implementation(projects.presentation.designSystem.text)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.previews)
    implementation(projects.presentation.topBars)
    implementation(projects.presentation.utils)
    ksp(libs.hilt.compiler)
    testImplementation(projects.presentation.paparazzi)
}
