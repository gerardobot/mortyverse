@file:Suppress("UnstableApiUsage")

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
    versionCatalogs {
        create("con") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "Config"
