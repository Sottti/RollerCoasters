plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
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
    implementation(libs.appcompat)
    implementation(libs.compose.activity)
    implementation(libs.compose.material)
    implementation(libs.compose.navigation)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.material)
    implementation(platform(libs.compose.bom))
    implementation(project(module.presentation.aboutMe))
    implementation(project(module.presentation.designSystem.icons))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.favourites))
    implementation(project(module.presentation.home))
    kapt(libs.hilt.compiler)
}