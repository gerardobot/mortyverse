package com.minroud.mortyverse.buildlogic.plugins.stack

import com.minroud.mortyverse.buildlogic.Libs
import com.minroud.mortyverse.buildlogic.implementation
import com.minroud.mortyverse.buildlogic.platform
import org.gradle.api.Plugin
import org.gradle.api.Project

class KoinPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        implementation(platform(Libs.Koin.bom))
        implementation(Libs.Koin.core)
    }
}
