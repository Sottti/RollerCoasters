plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.utils.format"
}

dependencies {
    testImplementation(libs.junit)
    testImplementation(libs.truth)
}