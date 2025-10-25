import com.sotti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.sotti.roller.coasters.app"
    androidResources {
        @Suppress("UnstableApiUsage")
        generateLocaleConfig = true
    }

    defaultConfig {
        applicationId = "com.sotti.roller.coasters"
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
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.hilt)
    implementation(libs.hilt.work)
    implementation(libs.splashscreen)
    implementation(libs.work.runtime)
    implementation(project(module.di))
    implementation(project(module.domain.systemFeatures))
    implementation(project(module.domain.rollerCoasters))
    implementation(project(module.domain.settings))
    implementation(project(module.presentation.home))
    ksp(libs.hilt.compiler)
}
