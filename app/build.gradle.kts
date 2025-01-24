import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.sottti.roller.coasters"

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
    implementation(libs.appcompat)
    implementation(libs.hilt)
    implementation(libs.splashscreen)
    implementation(project(module.data.settings))
    implementation(project(module.presentation.home))
    implementation(project(module.utils.device))
    kapt(libs.hilt.compiler)
}