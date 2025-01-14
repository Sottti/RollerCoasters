plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "co.sottti.roller.coasters.presentation.design.system.icons"
    compileSdk = 35
    defaultConfig { minSdk = 24 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.15" }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    api(platform(libs.compose.bom))
    implementation(libs.material)
}