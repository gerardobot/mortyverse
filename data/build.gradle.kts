plugins {
    alias(libs.plugins.mortyverse.jvm.library)
}

dependencies {
    implementation(projects.domain)

    testImplementation(libs.test.junit)
}
