plugins {
    alias(libs.plugins.mortyverse.android.library)
    alias(libs.plugins.ksp)
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)

    implementation(platform(libs.compose.bom))

    implementation(libs.coil)
    implementation(libs.coil.gif)

    implementation(libs.compose.ui)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    ksp(libs.ksp.moshi)

    debugImplementation(libs.debug.chucker)
    releaseImplementation(libs.release.chucker)

    testImplementation(libs.test.junit)
}
