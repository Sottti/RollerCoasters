import com.sottti.roller.coasters.buildSrc.Modules

plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(Modules.domain.rollerCoasters))
}