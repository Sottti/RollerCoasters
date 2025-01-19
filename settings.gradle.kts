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
    ":presentation:about-me",
    ":presentation:design-system:colors",
    ":presentation:design-system:dimensions",
    ":presentation:design-system:icons",
    ":presentation:design-system:playground",
    ":presentation:design-system:themes",
    ":presentation:design-system:typography",
    ":presentation:favourites",
    ":presentation:home",
    ":presentation:settings",
)