package com.minroud.mortyverse.buildlogic
import com.android.build.api.dsl.CompileOptions
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Packaging
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

object Config {
    const val appId = "com.minroud.mortyverse"

    const val compileSdk = 36
    const val minSdk = 28
    const val targetSdk = 36
    const val versionCode = 1
    const val versionName = "1.0"

    val javaVersion = JavaVersion.VERSION_17
    const val jvmToolchain = 17

    val excludes = listOf("/META-INF/{AL2.0,LGPL2.1}")

    const val implementation = "com.minroud.mortyverse.buildlogic.implementation"
    const val debugImplementation = "com.minroud.mortyverse.buildlogic.debugImplementation"
    const val testImplementation = "testImplementation"
}

internal fun CompileOptions.configureCompileOptions() {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

internal fun Packaging.configurePackaging() {
    resources.excludes += Config.excludes
}

internal fun LibraryExtension.configureNamespace(projectPath: String) {
    namespace = Config.appId + projectPath.replace(":", ".")
}

internal fun Project.configureKotlin() {
    extensions.configure<KotlinJvmProjectExtension> {
        jvmToolchain(Config.jvmToolchain)
        compilerOptions {
            configureCompiler()
        }
    }
}

internal fun Project.configureKotlinAndroid() {
    extensions.configure<KotlinAndroidProjectExtension> {
        jvmToolchain(Config.jvmToolchain)
        compilerOptions {
            configureCompiler()
        }
    }
}

internal fun KotlinCommonCompilerOptions.configureCompiler() {
    freeCompilerArgs.addAll(
        "-Xannotation-default-target=param-property",
        "-Xannotation-target-all"
    )
}
