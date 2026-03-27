import com.minroud.mortyverse.buildlogic.dsl.core

plugins {
    alias(libs.plugins.mortyverse.ui)
    alias(libs.plugins.mortyverse.compose.ui)
    alias(libs.plugins.mortyverse.koin.compose)
}

dependencies {
    core(projects).layers { domain() }

    implementation(libs.compose.navigation)
    implementation(libs.compose.material.icons.extended)
}
