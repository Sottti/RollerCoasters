plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.sottti.roller.coasters.presentation.design.system.icon.resources"
}

dependencies {
    implementation(projects.presentation.designSystem.colors)
    implementation(libs.appcompat)
}
