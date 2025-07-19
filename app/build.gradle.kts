import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.sottti.roller.coasters.app"

    androidResources {
        @Suppress("UnstableApiUsage")
        generateLocaleConfig = true
    }

    defaultConfig {
        applicationId = "com.sottti.roller.coasters"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    implementation(project(Modules.di))
    implementation(project(Modules.domain.features))
    implementation(project(Modules.domain.rollerCoasters))
    implementation(project(Modules.domain.settings))
    implementation(project(Modules.presentation.home))
    ksp(libs.hilt.compiler)
}