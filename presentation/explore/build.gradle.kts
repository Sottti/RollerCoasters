import com.sottti.roller.coasters.buildSrc.Modules

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
    api(project(Modules.domain.rollerCoasters))
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling)
    implementation(libs.hilt)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    implementation(platform(libs.compose.bom))
    implementation(project(Modules.domain.fixtures))
    implementation(project(Modules.presentation.designSystem.chip))
    implementation(project(Modules.presentation.designSystem.dimensions))
    implementation(project(Modules.presentation.designSystem.progressIndicators))
    implementation(project(Modules.presentation.designSystem.rollerCoasterCard))
    implementation(project(Modules.presentation.designSystem.text))
    implementation(project(Modules.presentation.designSystem.themes))
    implementation(project(Modules.presentation.empty))
    implementation(project(Modules.presentation.error))
    implementation(project(Modules.presentation.fixtures))
    implementation(project(Modules.presentation.format))
    implementation(project(Modules.presentation.imageLoading))
    implementation(project(Modules.presentation.previews))
    implementation(project(Modules.presentation.stringProvider))
    implementation(project(Modules.presentation.topBars))
    ksp(libs.hilt.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.mockk)
    testImplementation(libs.paging.testing)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
}