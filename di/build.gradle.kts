plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.di"
}

dependencies {
    implementation(projects.domain.rollerCoasters)
    implementation(libs.hilt)
    implementation(projects.data.systemFeatures)
    implementation(projects.data.rollerCoasters)
    implementation(projects.data.settings)
    implementation(projects.domain.systemFeatures)
    implementation(projects.domain.settings)
    ksp(libs.hilt.compiler)
}
