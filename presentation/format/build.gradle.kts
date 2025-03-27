import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.format"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.hilt)
    implementation(project(module.domain.rollerCoasters))
    implementation(project(module.utils.testStubs))
    ksp(libs.hilt.compiler)

    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.truth)
    androidTestRuntimeOnly(libs.test.runner)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}