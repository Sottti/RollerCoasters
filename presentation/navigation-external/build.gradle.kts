plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.navigation.external"
}

dependencies {
    implementation(libs.browser)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}