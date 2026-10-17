import org.gradle.api.Project
import java.util.Properties

private val mapsApiKeyProperty = "MAPS_API_KEY"

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
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
        manifestPlaceholders[mapsApiKeyProperty] = project.mapsApiKey()
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
    implementation(projects.di)
    implementation(projects.domain.systemFeatures)
    implementation(projects.domain.rollerCoasters)
    implementation(projects.domain.settings)
    implementation(projects.presentation.appShell)
    ksp(libs.hilt.compiler)
}

private fun Project.mapsApiKey(): String =
    providers.gradleProperty(mapsApiKeyProperty)
        .orElse(providers.environmentVariable(mapsApiKeyProperty))
        .orElse(providers.provider { localProperties().getProperty(mapsApiKeyProperty).orEmpty() })
        .get()

private fun Project.localProperties(): Properties {
    val properties = Properties()
    val localPropertiesFile = rootProject.file("local.properties")

    if (localPropertiesFile.isFile) {
        localPropertiesFile.inputStream().use(properties::load)
    }

    return properties
}
