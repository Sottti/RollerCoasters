plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.compose.material)
    implementation(platform(libs.compose.bom))
}