package com.minroud.mortyverse.buildlogic.plugins.stack

import com.minroud.mortyverse.buildlogic.Libs
import com.minroud.mortyverse.buildlogic.debugImplementation
import com.minroud.mortyverse.buildlogic.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project

class ComposeUiPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            pluginManager.apply(Libs.Plugins.Mortyverse.compose)

            implementation(Libs.Compose.ui)
            implementation(Libs.Compose.material3)
            implementation(Libs.Compose.preview)
            debugImplementation(Libs.Compose.Debug.tooling)
        }
    }
}
