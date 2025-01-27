import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.sottti.roller.coasters.data.settings"
}

dependencies {
    api(project(module.utils.device))
    implementation(libs.appcompat)
    implementation(libs.datastore.preferences)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}