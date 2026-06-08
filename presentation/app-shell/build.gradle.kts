plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.app.shell"
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation2)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(projects.domain.settings)
    implementation(projects.presentation.aboutMe)
    implementation(projects.presentation.designSystem.icons)
    implementation(projects.presentation.designSystem.text)
    implementation(projects.presentation.designSystem.themes)
    implementation(projects.presentation.explore)
    implementation(projects.presentation.favourites)
    implementation(projects.presentation.navigation)
    implementation(projects.presentation.previews)
    implementation(projects.presentation.rollerCoasterDetails)
    implementation(projects.presentation.search)
    implementation(projects.presentation.settings)
    implementation(projects.presentation.utils)
    ksp(libs.hilt.compiler)
    testImplementation(projects.presentation.paparazzi)
}
