import com.minroud.mortyverse.buildlogic.dsl.core

plugins {
    alias(libs.plugins.mortyverse.domain)
    alias(libs.plugins.mortyverse.koin)
}

dependencies {
    core(projects).layers { domain() }
}
