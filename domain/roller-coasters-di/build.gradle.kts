import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.domain.roller.coasters.di"
}

dependencies {
    api(libs.result)
    api(project(module.domain.rollerCoasters))
    implementation(libs.hilt)
    implementation(project(module.data.rollerCoasters))
    ksp(libs.hilt.compiler)
}