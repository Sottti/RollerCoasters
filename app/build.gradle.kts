import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
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
    implementation(libs.hilt.work)
    implementation(libs.splashscreen)
    implementation(project(module.data.workManager)) // should be removed when issues resolved
    implementation(project(module.di))
    implementation(project(module.domain.features))
    implementation(project(module.domain.settings))
    implementation(project(module.presentation.home))
    ksp(libs.hilt.compiler)
}