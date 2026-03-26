package com.minroud.mortyverse.buildlogic.plugins.base

import com.minroud.mortyverse.buildlogic.Libs
import com.minroud.mortyverse.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project

class KotlinLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(Libs.Plugins.javaLibrary)
        pluginManager.apply(Libs.Plugins.jvm)
        pluginManager.apply(Libs.Plugins.ktlint)

        configureKotlin()
    }
}
