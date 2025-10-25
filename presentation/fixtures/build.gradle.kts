import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.fixtures"
}

dependencies {
    implementation(project(module.domain.model))
}
