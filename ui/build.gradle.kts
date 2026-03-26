plugins {
    alias(libs.plugins.mortyverse.ui)
    alias(libs.plugins.mortyverse.compose.ui)
    alias(libs.plugins.mortyverse.koin.compose)
}

dependencies {
    implementation(projects.domain)
    implementation(projects.infra)

    implementation(libs.compose.navigation)
    implementation(libs.compose.material.icons.extended)
}
