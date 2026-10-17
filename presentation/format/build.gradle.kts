plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.format"
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)
    implementation(libs.hilt)
    implementation(platform(libs.compose.bom))
    implementation(projects.domain.fixtures)
    implementation(projects.domain.locales)
    implementation(projects.domain.rollerCoasters)
    implementation(projects.presentation.designSystem.colors)
    implementation(projects.presentation.fixtures)
    ksp(libs.hilt.compiler)

    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.truth)
    androidTestRuntimeOnly(libs.test.runner)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}
