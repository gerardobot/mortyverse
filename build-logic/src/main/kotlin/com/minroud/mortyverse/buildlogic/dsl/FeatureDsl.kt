package com.minroud.mortyverse.buildlogic.dsl

import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.feature(feature: Any) =
    FeatureDependencyBuilder(this, feature)

fun DependencyHandlerScope.core(projects: Any) =
    FeatureDependencyBuilder(this, projects)

class FeatureDependencyBuilder(
    private val deps: DependencyHandlerScope,
    private val feature: Any
) {
    fun layers(block: FeatureDependencyScope.() -> Unit) {
        FeatureDependencyScope(deps, feature).apply(block)
    }
}

class FeatureDependencyScope(
    private val deps: DependencyHandlerScope,
    private val feature: Any
) {

    fun domain() = add("domain")
    fun data() = add("data")
    fun ui() = add("ui")
    fun infra() = add("infra")

    private fun add(name: String) {
        val value = feature
            .javaClass
            .methods
            .first { it.name == "get${name.replaceFirstChar { it.uppercase() }}" }
            .invoke(feature)

        deps.add("implementation", value)
    }
}
