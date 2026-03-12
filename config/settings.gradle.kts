pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    versionCatalogs {
        create("con") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "Config"
