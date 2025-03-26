import com.sottti.roller.coasters.buildSrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    api(project(module.domain.rollerCoasters))
}