package com.minroud.mortyverse.buildlogic.plugins.architecture

import com.minroud.mortyverse.buildlogic.Libs
import org.gradle.api.Plugin
import org.gradle.api.Project

class InfraPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(Libs.Plugins.Mortyverse.androidLibrary)
    }
}
