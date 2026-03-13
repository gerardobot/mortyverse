plugins {
    alias(libs.plugins.mortyverse.android.library)
}

dependencies {
    implementation(projects.domain)
    implementation(projects.infra)

    implementation(platform(libs.compose.bom))
    implementation(platform(libs.koin.bom))

    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.navigation)
    implementation(libs.compose.preview)
    implementation(libs.compose.ui)

    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.koin.androidx.compose)

    debugImplementation(libs.debug.compose.tooling)
}
