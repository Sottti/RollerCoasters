plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "co.sottti.roller.coasters.presentation.design.system.icons"
}

dependencies {
    api(platform(libs.compose.bom))
    implementation(libs.material)
}