@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")

    repositories {
        google()
        mavenCentral()
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

rootProject.name = "Mortyverse"

include(
    ":app",
    ":domain",
    ":data",
    ":framework",
    ":ui"
)

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
