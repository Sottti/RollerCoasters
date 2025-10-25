plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sotti.roller.coasters.presentation.string.provider"
}

dependencies {
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
}
