plugins {
    alias(libs.plugins.mortyverse.infra)
    alias(libs.plugins.mortyverse.compose)
    alias(libs.plugins.mortyverse.koin)
    alias(libs.plugins.mortyverse.retrofit.serialization)
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)

    implementation(libs.compose.ui)

    implementation(libs.coil)
    implementation(libs.coil.gif)

    debugImplementation(libs.debug.chucker)
    releaseImplementation(libs.release.chucker)
}
