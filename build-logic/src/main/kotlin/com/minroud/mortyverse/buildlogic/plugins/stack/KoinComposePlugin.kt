package com.minroud.mortyverse.buildlogic.plugins.stack

import com.minroud.mortyverse.buildlogic.Libs
import com.minroud.mortyverse.buildlogic.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project

class KoinComposePlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(Libs.Plugins.Mortyverse.koin)

        implementation(Libs.Koin.androidCompose)
    }
}
