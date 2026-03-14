plugins {
    alias(libs.plugins.mortyverse.android.application)
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
