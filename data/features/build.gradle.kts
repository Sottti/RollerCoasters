import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.data.features"
}

dependencies {
    implementation(libs.hilt)
    implementation(project(module.domain.features))
    ksp(libs.hilt.compiler)
}