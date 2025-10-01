plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.typography"

}

dependencies {
    implementation(libs.compose.material)
    implementation(platform(libs.compose.bom))
}
