plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.utils"
}

dependencies {
    implementation(libs.compose.foundation)
    implementation(libs.compose.material)
    implementation(libs.compose.ui)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.lifecycle.viewModel)
    implementation(platform(libs.compose.bom))
}
