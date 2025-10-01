import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.fixtures"

}

dependencies {
    implementation(libs.compose.foundation)
    implementation(platform(libs.compose.bom))
    implementation(project(module.domain.model))
}
