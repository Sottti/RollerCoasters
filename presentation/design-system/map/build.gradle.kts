plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.map"
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.maps)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(platform(libs.compose.bom))
    implementation(projects.domain.fixtures)
    implementation(projects.presentation.designSystem.images)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.previews)
    testImplementation(projects.presentation.paparazzi)
}
