import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.di"
}

dependencies {
    api(project(Modules.domain.rollerCoasters))
    implementation(libs.hilt)
    implementation(project(Modules.data.features))
    implementation(project(Modules.data.rollerCoasters))
    implementation(project(Modules.data.settings))
    implementation(project(Modules.domain.features))
    implementation(project(Modules.domain.settings))
    ksp(libs.hilt.compiler)
}