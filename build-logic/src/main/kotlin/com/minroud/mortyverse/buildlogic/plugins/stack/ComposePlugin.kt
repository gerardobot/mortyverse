package com.minroud.mortyverse.buildlogic.plugins.stack

import com.minroud.mortyverse.buildlogic.Libs
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.minroud.mortyverse.buildlogic.implementation
import com.minroud.mortyverse.buildlogic.platform
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply(Libs.Plugins.compose)

            implementation(platform(Libs.Compose.bom))

            extensions.findByType(ApplicationExtension::class.java)?.apply {
                buildFeatures.compose = true
            }

            extensions.findByType(LibraryExtension::class.java)?.apply {
                buildFeatures.compose = true
            }
        }
    }
}
