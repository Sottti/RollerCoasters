plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.navigation"
}

dependencies {
    implementation(libs.compose.navigation2)
    implementation(libs.kotlin.serialization.json)
}