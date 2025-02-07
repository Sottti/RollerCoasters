plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.roller.coasters.data.remote.core"
}

dependencies {
    api(libs.ktor.client.core)
    api(libs.ktor.serialization.kotlinx.json)
    implementation(libs.hilt)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    kapt(libs.hilt.compiler)
}