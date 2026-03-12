plugins {
    alias(libs.plugins.android.library)
    id("mortyverse.android.library")
}

android {
    namespace = "com.minroud.mortyverse.ui"
}

dependencies {
    implementation(projects.domain)
    implementation(projects.framework)

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
