plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.sottti.roller.coasters.domain.settings"
}

dependencies {
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}