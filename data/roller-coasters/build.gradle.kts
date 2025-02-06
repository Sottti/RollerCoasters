import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.roller.coasters.data.roller.coasters"
}

dependencies {
    implementation(libs.hilt)
    implementation(project(module.data.network))
    kapt(libs.hilt.compiler)
}