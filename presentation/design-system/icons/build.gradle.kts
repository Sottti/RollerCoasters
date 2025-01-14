plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "co.sottti.roller.coasters.presentation.design.system.icons"
    compileSdk = 35
    defaultConfig { minSdk = 24 }
}

dependencies {
    api(platform(libs.compose.bom))
    api(libs.compose.material.icons.core)
}