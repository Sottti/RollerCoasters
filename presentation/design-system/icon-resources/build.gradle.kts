import com.sotti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sotti.roller.coasters.presentation.design.system.icon.resources"
}

dependencies {
    implementation(project(module.presentation.designSystem.colors))
    implementation(libs.appcompat)
}
