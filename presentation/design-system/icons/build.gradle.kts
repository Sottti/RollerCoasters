plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "co.sottti.roller.coasters.presentation.design.system.icons"
}

dependencies {
    implementation(libs.material)
}