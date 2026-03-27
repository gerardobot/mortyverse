import com.minroud.mortyverse.buildlogic.dsl.core
import com.minroud.mortyverse.buildlogic.dsl.feature

plugins {
    alias(libs.plugins.mortyverse.ui)
    alias(libs.plugins.mortyverse.compose.ui)
    alias(libs.plugins.mortyverse.koin.compose)
}

dependencies {
    core(projects).layers { domain(); ui(); infra() }
    feature(projects.feature.characters).layers { domain() }

    implementation(libs.compose.navigation)
}
