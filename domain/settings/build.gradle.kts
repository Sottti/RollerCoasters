import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.annotations)
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines.core)
    implementation(project(module.domain.features))
    implementation(project(module.domain.locales))

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}