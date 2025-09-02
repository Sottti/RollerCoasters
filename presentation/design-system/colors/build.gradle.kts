import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.colors"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    api(project(module.domain.locales))
    api(project(module.domain.settings))
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)
    implementation(libs.compose.ui)
    implementation(libs.core)
    implementation(platform(libs.compose.bom))

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}