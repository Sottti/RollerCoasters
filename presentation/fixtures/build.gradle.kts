import com.sottti.roller.coasters.buildsrc.module

plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.fixtures"
}

dependencies {
    implementation(project(module.domain.model))
}
