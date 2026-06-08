plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.fixtures"
}

dependencies {
    implementation(projects.domain.model)
}
