import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.about.me"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.presentation.designSystem.cardGrid))
    implementation(project(Modules.presentation.designSystem.colors))
    implementation(project(Modules.presentation.designSystem.dimensions))
    implementation(project(Modules.presentation.designSystem.heroImage))
    implementation(project(Modules.presentation.designSystem.icons))
    implementation(project(Modules.presentation.designSystem.images))
    implementation(project(Modules.presentation.designSystem.text))
    implementation(project(Modules.presentation.designSystem.themes))
    implementation(project(Modules.presentation.format))
    implementation(project(Modules.presentation.navigationExternal))
    implementation(project(Modules.presentation.previews))
    implementation(project(Modules.presentation.topBars))
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}