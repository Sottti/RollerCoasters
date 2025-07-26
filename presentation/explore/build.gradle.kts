import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.explore"
    buildFeatures { compose = true }
    @Suppress("UnstableApiUsage")
    composeOptions { kotlinCompilerExtensionVersion = kotlinCompilerExtensionVersion }
}

dependencies {
    api(project(module.domain.rollerCoasters))
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    implementation(platform(libs.compose.bom))
    implementation(project(module.domain.fixtures))
    implementation(project(module.presentation.designSystem.chip))
    implementation(project(module.presentation.designSystem.dimensions))
    implementation(project(module.presentation.designSystem.progressIndicators))
    implementation(project(module.presentation.designSystem.rollerCoasterCard))
    implementation(project(module.presentation.designSystem.text))
    implementation(project(module.presentation.designSystem.themes))
    implementation(project(module.presentation.empty))
    implementation(project(module.presentation.error))
    implementation(project(module.presentation.fixtures))
    implementation(project(module.presentation.format))
    implementation(project(module.presentation.imageLoading))
    implementation(project(module.presentation.previews))
    implementation(project(module.presentation.stringProvider))
    implementation(project(module.presentation.topBars))
    implementation(project(module.presentation.utils))
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.paging.testing)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}