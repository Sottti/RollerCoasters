import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.utils.device"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(project(module.domain.settings))
    implementation(libs.annotations)
    implementation(libs.appcompat)
    implementation(libs.hilt)
    implementation(project(module.domain.features))
    ksp(libs.hilt.compiler)

    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.mockk.android) {
        exclude(group = "org.junit.jupiter")
        exclude(group = "org.junit.vintage")
    }
    androidTestImplementation(libs.truth)
    androidTestRuntimeOnly(libs.test.runner)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)

}