import com.sottti.roller.coasters.buildsrc.module

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(module.domain.rollerCoasters))
}
