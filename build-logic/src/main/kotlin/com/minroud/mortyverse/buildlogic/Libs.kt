package com.minroud.mortyverse.buildlogic
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension

internal object Libs {
    object Koin {
        const val bom = "koin-bom"
        const val core = "koin-core"
        const val androidCompose = "koin-androidx-compose"
    }

    object Compose {
        const val bom = "compose-bom"
        const val activity = "compose-activity"
        const val navigation = "compose-navigation"
        const val ui = "compose-ui"
        const val material3 = "compose-material3"
        const val preview = "compose-preview"

        object Debug{
            const val tooling = "debug-compose-tooling"
        }
    }

    object Retrofit {
        const val retrofit = "retrofit"
        const val converterSerialization = "retrofit-converter-serialization"
    }

    object Kotlinx {
        const val serialization = "kotlinx-serialization-json"
    }

    object Plugins {
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val compose = "org.jetbrains.kotlin.plugin.compose"
        const val ktlint = "org.jlleitschuh.gradle.ktlint"
        const val javaLibrary = "java-library"
        const val jvm = "org.jetbrains.kotlin.jvm"
        const val kotlinSerialization = "kotlinx-serialization"

        object Mortyverse {
            const val kotlinLibrary = "mortyverse.kotlin.library"
            const val androidLibrary = "mortyverse.android.library"
            const val compose = "mortyverse.compose"
            const val koin = "mortyverse.koin"
        }
    }
}

private fun Project.lib(ref: String) =
    extensions.getByType(VersionCatalogsExtension::class.java)
        .named("libs")
        .findLibrary(ref)
        .orElseThrow()

private fun Project.resolve(dep: Any): Any =
    if (dep is String) lib(dep) else dep

internal fun Project.implementation(dep: Any) {
    dependencies.add("implementation", resolve(dep))
}

internal fun Project.debugImplementation(ref: Any) {
    dependencies.add("debugImplementation", resolve(ref))
}

internal fun Project.platform(ref: String): Any =
    dependencies.platform(lib(ref).get())
