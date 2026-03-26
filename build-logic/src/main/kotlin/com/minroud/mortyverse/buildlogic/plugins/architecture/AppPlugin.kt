package com.minroud.mortyverse.buildlogic.plugins.architecture

import com.minroud.mortyverse.buildlogic.Config
import com.minroud.mortyverse.buildlogic.Libs
import com.android.build.api.dsl.ApplicationExtension
import com.minroud.mortyverse.buildlogic.configureCompileOptions
import com.minroud.mortyverse.buildlogic.configureKotlinAndroid
import com.minroud.mortyverse.buildlogic.configurePackaging
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AppPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(Libs.Plugins.androidApplication)

        extensions.configure<ApplicationExtension> {
            namespace = Config.appId
            compileSdk = Config.compileSdk

            defaultConfig {
                applicationId = Config.appId
                minSdk = Config.minSdk
                targetSdk = Config.targetSdk
                versionCode = Config.versionCode
                versionName = Config.versionName
            }

            compileOptions { configureCompileOptions() }

            packaging { configurePackaging() }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

        configureKotlinAndroid()
    }
}
