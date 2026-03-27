package com.minroud.mortyverse.buildlogic.plugins.stack

import com.minroud.mortyverse.buildlogic.Libs
import com.minroud.mortyverse.buildlogic.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project

class RetrofitSerializationPlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
        pluginManager.apply(Libs.Plugins.kotlinSerialization)

        implementation(Libs.Retrofit.retrofit)
        implementation(Libs.Retrofit.converterSerialization)
        implementation(Libs.Kotlinx.serialization)
    }
}
