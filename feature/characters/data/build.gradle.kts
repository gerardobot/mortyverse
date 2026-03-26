import com.minroud.mortyverse.buildlogic.dsl.core
import com.minroud.mortyverse.buildlogic.dsl.feature

plugins {
    alias(libs.plugins.mortyverse.data)
    alias(libs.plugins.mortyverse.koin)
}

dependencies {
    core(projects).layers { domain() }
    feature(projects.feature.characters).layers { domain() }
}
