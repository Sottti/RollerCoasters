import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.sottti.roller.coasters.data.work.manager"
}

dependencies {
    implementation(libs.hilt)
    implementation(libs.hilt.work)
    implementation(libs.work.runtime)
    implementation(project(module.data.rollerCoasters))
    kapt(libs.hilt.compiler)
}