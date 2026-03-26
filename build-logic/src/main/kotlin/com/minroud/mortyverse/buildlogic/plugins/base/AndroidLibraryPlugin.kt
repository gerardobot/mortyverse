package com.minroud.mortyverse.buildlogic.plugins.base

import com.android.build.api.dsl.LibraryExtension
import com.minroud.mortyverse.buildlogic.Config
import com.minroud.mortyverse.buildlogic.Libs
import com.minroud.mortyverse.buildlogic.configureCompileOptions
import com.minroud.mortyverse.buildlogic.configureKotlinAndroid
import com.minroud.mortyverse.buildlogic.configureNamespace
import com.minroud.mortyverse.buildlogic.configurePackaging
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(Libs.Plugins.androidLibrary)
        pluginManager.apply(Libs.Plugins.ktlint)

        extensions.configure<LibraryExtension> {
            configureNamespace(path)

            compileSdk = Config.compileSdk

            defaultConfig { minSdk = Config.minSdk }

            compileOptions { configureCompileOptions() }

            packaging { configurePackaging() }
        }

        configureKotlinAndroid()
    }
}
