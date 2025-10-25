import com.sotti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(module.domain.model))
    implementation(libs.annotations)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.truth)
}
