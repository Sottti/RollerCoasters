import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(libs.result)
    api(project(module.domain.model))
    api(project(module.domain.settings))
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.paging.common)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
    testImplementation(project(module.utils.testStubs))
}