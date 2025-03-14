import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.data.settings"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    api(project(module.domain.model))
    api(project(module.utils.device))
    implementation(libs.appcompat)
    implementation(libs.datastore.preferences)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    androidTestImplementation(libs.coroutines.test)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.mockk.android) {
        exclude(group = "org.junit.jupiter")
        exclude(group = "org.junit.vintage")
    }
    androidTestImplementation(libs.truth)
    androidTestRuntimeOnly(libs.test.runner)

    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}