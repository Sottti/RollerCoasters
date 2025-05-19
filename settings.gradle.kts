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
    ":di",
    ":domain:features",
    ":domain:fixtures",
    ":domain:model",
    ":domain:roller-coasters",
    ":domain:settings",
    ":presentation:about-me",
    ":presentation:design-system:card-grid",
    ":presentation:design-system:chip",
    ":presentation:design-system:colors",
    ":presentation:design-system:dialogs",
    ":presentation:design-system:dimensions",
    ":presentation:design-system:icons",
    ":presentation:design-system:illustrations",
    ":presentation:design-system:images",
    ":presentation:design-system:map",
    ":presentation:design-system:playground",
    ":presentation:design-system:profile-picture",
    ":presentation:design-system:progress-indicators",
    ":presentation:design-system:roller-coaster-card",
    ":presentation:design-system:switch",
    ":presentation:design-system:text",
    ":presentation:design-system:themes",
    ":presentation:design-system:typography",
    ":presentation:empty",
    ":presentation:error",
    ":presentation:explore",
    ":presentation:favourites",
    ":presentation:fixtures",
    ":presentation:format",
    ":presentation:home",
    ":presentation:image-loading",
    ":presentation:informative",
    ":presentation:navigation",
    ":presentation:navigation-external",
    ":presentation:previews",
    ":presentation:search",
    ":presentation:roller-coaster-details",
    ":presentation:settings",
    ":presentation:string-provider",
    ":presentation:top-bars",
    ":utils:time-dates",
)