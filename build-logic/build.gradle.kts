plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

gradlePlugin {
    plugins {
        register("kotlinLibrary") {
            id = "mortyverse.kotlin.library"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.base.KotlinLibraryPlugin"
        }

        register("androidLibrary") {
            id = "mortyverse.android.library"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.base.AndroidLibraryPlugin"
        }

        register("app") {
            id = "mortyverse.app"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.architecture.AppPlugin"
        }

        register("ui") {
            id = "mortyverse.ui"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.architecture.UiPlugin"
        }

        register("domain") {
            id = "mortyverse.domain"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.architecture.DomainPlugin"
        }

        register("data") {
            id = "mortyverse.data"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.architecture.DataPlugin"
        }

        register("infra") {
            id = "mortyverse.infra"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.architecture.InfraPlugin"
        }

        register("compose") {
            id = "mortyverse.compose"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.stack.ComposePlugin"
        }

        register("compose-app") {
            id = "mortyverse.compose.app"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.stack.ComposeAppPlugin"
        }

        register("compose-ui") {
            id = "mortyverse.compose.ui"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.stack.ComposeUiPlugin"
        }

        register("koin") {
            id = "mortyverse.koin"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.stack.KoinPlugin"
        }

        register("koin-compose") {
            id = "mortyverse.koin.compose"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.stack.KoinComposePlugin"
        }

        register("retrofit-serialization") {
            id = "mortyverse.retrofit.serialization"
            implementationClass = "com.minroud.mortyverse.buildlogic.plugins.stack.RetrofitSerializationPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.compose.compiler.gradle.plugin)
}
