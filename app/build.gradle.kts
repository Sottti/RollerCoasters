plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.sottti.roller.coasters"
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }

    defaultConfig {
        applicationId = "com.sottti.roller.coasters"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.compose.activity)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(platform(libs.compose.bom))
    implementation(project(":presentation:about-me"))
    implementation(project(":presentation:design-system:icons"))
    implementation(project(":presentation:design-system:themes"))
    implementation(project(":presentation:favourites"))
    implementation(project(":presentation:home"))
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)
}