plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.sottti.roller.coasters.utils.lifecycle"
}

dependencies {
    implementation(libs.kotlin.coroutines.core)
}
