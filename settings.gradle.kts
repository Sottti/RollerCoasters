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
    ":data:features",
    ":data:network",
    ":data:roller-coasters",
    ":data:settings",
    ":data:work-manager",
    ":di",
    ":domain:features",
    ":domain:model",
    ":domain:roller-coasters",
    ":domain:settings",
    ":presentation:about-me",
    ":presentation:design-system:chip",
    ":presentation:design-system:colors",
    ":presentation:design-system:dialogs",
    ":presentation:design-system:dimensions",
    ":presentation:design-system:icons",
    ":presentation:design-system:playground",
    ":presentation:design-system:progress-indicators",
    ":presentation:design-system:roller-coaster-card",
    ":presentation:design-system:switch",
    ":presentation:design-system:text",
    ":presentation:design-system:themes",
    ":presentation:design-system:typography",
    ":presentation:explore",
    ":presentation:favourites",
    ":presentation:home",
    ":presentation:image-loading",
    ":presentation:navigation",
    ":presentation:previews",
    ":presentation:roller-coaster-details",
    ":presentation:settings",
    ":presentation:top-bars",
    ":presentation:utils:format",
    ":utils:time-dates",
)