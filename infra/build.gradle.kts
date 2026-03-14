plugins {
    alias(libs.plugins.mortyverse.android.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)

    implementation(platform(libs.compose.bom))

    implementation(libs.coil)
    implementation(libs.coil.gif)

    implementation(libs.compose.ui)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.kotlinx.serialization.json)

    debugImplementation(libs.debug.chucker)
    releaseImplementation(libs.release.chucker)

    testImplementation(libs.test.junit)
}
