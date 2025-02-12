import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.image.loading"
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    api(project(module.domain.model))
    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor3)
    implementation(libs.startup.runtime)
    implementation(project(module.data.network))

}