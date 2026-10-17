plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.informative"
}

dependencies {
    api(projects.presentation.designSystem.illustrations)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(platform(libs.compose.bom))
    implementation(projects.presentation.designSystem.colors)
    implementation(projects.presentation.designSystem.dimensions)
    implementation(projects.presentation.designSystem.text)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.fixtures)
    implementation(projects.presentation.previews)
    implementation(projects.presentation.utils)
    testImplementation(projects.presentation.paparazzi)
}
