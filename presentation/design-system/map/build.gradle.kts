import com.sottti.roller.coasters.buildsrc.module

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
    implementation(project(module.domain.fixtures))
    implementation(project(module.presentation.designSystem.images))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.previews))
    testImplementation(project(module.presentation.paparazzi))
}
