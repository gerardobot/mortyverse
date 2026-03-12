plugins {
    id("mortyverse.jvm.library")
}

dependencies {
    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.test.kotlin)
    testImplementation(libs.test.kotlinx.coroutines)
    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockk)
}
