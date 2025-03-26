import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.roller.coasters.data.roller.coasters"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(libs.hilt)
    implementation(libs.paging.runtime)
    implementation(libs.room)
    implementation(libs.room.paging)
    implementation(libs.startup.runtime)
    implementation(libs.work.runtime)
    implementation(project(module.data.network))
    implementation(project(module.domain.rollerCoasters))
    implementation(project(module.utils.testStubs))
    implementation(project(module.utils.timeDates))
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.paging.common)
    testImplementation(libs.truth)

    androidTestImplementation(libs.core)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.kotlin.coroutines.test)
    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.truth)
    androidTestRuntimeOnly(libs.test.runner)
}