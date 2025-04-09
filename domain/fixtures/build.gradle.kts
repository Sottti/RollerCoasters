import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(module.domain.rollerCoasters))
}