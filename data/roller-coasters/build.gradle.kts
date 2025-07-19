import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.sottti.roller.coasters.data.roller.coasters"
}

dependencies {
    implementation(libs.hilt)
    implementation(libs.paging.runtime)
    implementation(libs.room)
    implementation(libs.room.paging)
    implementation(libs.startup.runtime)
    implementation(libs.work.runtime)
    implementation(project(Modules.data.network))
    implementation(project(Modules.domain.fixtures))
    implementation(project(Modules.domain.rollerCoasters))
    implementation(project(Modules.utils.timeDates))
    ksp(libs.hilt.compiler)
    ksp(libs.room.compiler)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.paging.common)
    testImplementation(libs.truth)
}