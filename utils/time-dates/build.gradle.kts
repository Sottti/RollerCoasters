import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(Modules.domain.model))
    implementation(libs.annotations)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.truth)
}