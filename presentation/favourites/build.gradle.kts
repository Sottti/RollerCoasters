plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.favourites"
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    implementation(platform(libs.compose.bom))
    implementation(projects.domain.fixtures)
    implementation(projects.domain.rollerCoasters)
    implementation(projects.presentation.designSystem.dimensions)
    implementation(projects.presentation.designSystem.empty)
    implementation(projects.presentation.designSystem.error)
    implementation(projects.presentation.designSystem.progressIndicators)
    implementation(projects.presentation.designSystem.rollerCoasterCard)
    implementation(projects.presentation.designSystem.text)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.previews)
    implementation(projects.presentation.topBars)
    implementation(projects.presentation.utils)
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.paging.testing)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(projects.presentation.paparazzi)
}
