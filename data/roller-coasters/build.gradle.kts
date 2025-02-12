import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.roller.coasters.data.roller.coasters"
}

dependencies {
    api(project(module.domain.model))
    implementation(libs.hilt)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    implementation(project(module.data.network))
    implementation(project(module.utils.dates))
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)
}