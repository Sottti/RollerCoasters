rootProject.name = "Roller Coasters"

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
include(
    ":app",
    ":data:network",
    ":data:roller-coasters",
    ":data:settings",
    ":data:work-manager",
    ":domain:model",
    ":presentation:about-me",
    ":presentation:design-system:colors",
    ":presentation:design-system:dialogs",
    ":presentation:design-system:dimensions",
    ":presentation:design-system:icons",
    ":presentation:design-system:loading",
    ":presentation:design-system:playground",
    ":presentation:design-system:switch",
    ":presentation:design-system:text",
    ":presentation:design-system:themes",
    ":presentation:design-system:typography",
    ":presentation:explore",
    ":presentation:favourites",
    ":presentation:home",
    ":presentation:image-loading",
    ":presentation:settings",
    ":utils:dates",
    ":utils:device",
)