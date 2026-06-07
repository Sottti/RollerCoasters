import com.sottti.roller.coasters.buildsrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.di"
}

dependencies {
    implementation(project(module.domain.rollerCoasters))
    implementation(libs.hilt)
    implementation(project(module.data.systemFeatures))
    implementation(project(module.data.rollerCoasters))
    implementation(project(module.data.settings))
    implementation(project(module.domain.systemFeatures))
    implementation(project(module.domain.settings))
    ksp(libs.hilt.compiler)
}
