plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.about.me"
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(projects.presentation.designSystem.cardGrid)
    implementation(projects.presentation.designSystem.colors)
    implementation(projects.presentation.designSystem.dimensions)
    implementation(projects.presentation.designSystem.heroImage)
    implementation(projects.presentation.designSystem.icons)
    implementation(projects.presentation.designSystem.images)
    implementation(projects.presentation.designSystem.shapes)
    implementation(projects.presentation.designSystem.text)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.format)
    implementation(projects.presentation.navigationExternal)
    implementation(projects.presentation.previews)
    implementation(projects.presentation.topBars)
    implementation(projects.presentation.utils)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(projects.presentation.paparazzi)
}
