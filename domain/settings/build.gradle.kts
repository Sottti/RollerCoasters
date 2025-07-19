import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(libs.annotations)
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines.core)
    implementation(project(Modules.domain.features))

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockk)
    testImplementation(libs.truth)
}