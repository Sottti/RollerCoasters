plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.previews"
}

dependencies {
    implementation(libs.compose.ui.tooling)
    implementation(platform(libs.compose.bom))
}
