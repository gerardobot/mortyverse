import com.minroud.mortyverse.buildlogic.dsl.core
import com.minroud.mortyverse.buildlogic.dsl.feature

plugins {
    alias(libs.plugins.mortyverse.app)
    alias(libs.plugins.mortyverse.compose.app)
    alias(libs.plugins.mortyverse.koin.compose)
}

dependencies {
    core(projects).layers { domain(); ui(); infra() }
    feature(projects.feature.characters).layers { domain(); data(); ui(); infra() }
    feature(projects.feature.demo).layers { ui() }
}
