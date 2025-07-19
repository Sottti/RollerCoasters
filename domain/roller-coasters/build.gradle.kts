import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(libs.result)
    api(project(Modules.domain.model))
    api(project(Modules.domain.settings))
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.paging.common)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.paging.common)
    testImplementation(libs.paging.testing)
    testImplementation(libs.truth)
    testImplementation(project(Modules.domain.fixtures))
}