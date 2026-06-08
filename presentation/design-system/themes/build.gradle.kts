plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.themes"
}

dependencies {
    api(projects.presentation.designSystem.colors)
    implementation(libs.compose.material)
    implementation(libs.material)
    implementation(libs.splashscreen)
    implementation(platform(libs.compose.bom))
    implementation(projects.presentation.designSystem.dimensions)
    implementation(projects.presentation.designSystem.iconResources)
    implementation(projects.presentation.designSystem.shapes)
    implementation(projects.presentation.designSystem.typography)
}
