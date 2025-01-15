rootProject.name = "Roller Coasters"

pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
include(
    ":app",
    ":presentation:about-me",
    ":presentation:design-system:icons",
    ":presentation:design-system:themes",
    ":presentation:favourites",
    ":presentation:home",
)