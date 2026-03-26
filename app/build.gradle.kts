plugins {
    alias(libs.plugins.mortyverse.app)
    alias(libs.plugins.mortyverse.compose.app)
    alias(libs.plugins.mortyverse.koin.compose)
}

dependencies {
    implementation(projects.ui)
    implementation(projects.domain)
    implementation(projects.data)
    implementation(projects.infra)

    implementation(platform(libs.koin.bom))
    implementation(libs.compose.activity)

    implementation(libs.koin.android)

    testImplementation(libs.test.junit)
}
