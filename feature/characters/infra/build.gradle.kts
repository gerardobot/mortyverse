import com.minroud.mortyverse.buildlogic.dsl.core
import com.minroud.mortyverse.buildlogic.dsl.feature

plugins {
    alias(libs.plugins.mortyverse.infra)
    alias(libs.plugins.mortyverse.koin)
    alias(libs.plugins.mortyverse.retrofit.serialization)
}

dependencies {
    core(projects).layers { domain(); infra() }
    feature(projects.feature.characters).layers { domain(); data() }
}
