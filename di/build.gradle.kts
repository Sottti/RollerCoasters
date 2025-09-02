import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.di"
}

dependencies {
    api(project(module.domain.rollerCoasters))
    implementation(libs.hilt)
    implementation(project(module.data.features))
    implementation(project(module.data.rollerCoasters))
    implementation(project(module.data.settings))
    implementation(project(module.domain.features))
    implementation(project(module.domain.settings))
    ksp(libs.hilt.compiler)
}