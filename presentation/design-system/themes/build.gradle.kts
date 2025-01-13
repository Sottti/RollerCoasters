plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "co.sottti.roller.coasters.presentation.design.system.themes"
    compileSdk = 35
    defaultConfig { minSdk = 24 }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
}