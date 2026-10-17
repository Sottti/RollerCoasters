plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.search.bar"
}

dependencies {
    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(platform(libs.compose.bom))
    implementation(projects.domain.fixtures)
    implementation(projects.presentation.designSystem.dimensions)
    implementation(projects.presentation.designSystem.icons)
    implementation(projects.presentation.designSystem.progressIndicators)
    implementation(projects.presentation.designSystem.shapes)
    implementation(projects.presentation.designSystem.text)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.previews)
    testImplementation(projects.presentation.paparazzi)
}
