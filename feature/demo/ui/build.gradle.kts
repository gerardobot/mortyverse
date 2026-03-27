import com.minroud.mortyverse.buildlogic.dsl.core

plugins {
    alias(libs.plugins.mortyverse.ui)
    alias(libs.plugins.mortyverse.compose.ui)
}

dependencies {
    core(projects).layers { domain(); ui() }

    implementation(libs.compose.navigation)
}
