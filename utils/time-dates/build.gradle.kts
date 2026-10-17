plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(projects.domain.model)
    implementation(libs.annotations)

    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.truth)
}
