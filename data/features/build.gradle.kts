import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.data.features"
}

dependencies {
    implementation(libs.hilt)
    implementation(project(Modules.domain.features))
    ksp(libs.hilt.compiler)
}