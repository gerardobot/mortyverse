plugins {
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ktlint)
    alias(libs.plugins.android.library) apply false
}

ktlint {
    android.set(true)
    outputToConsole.set(true)

    filter {
        exclude("**/build/**")
        exclude("**/generated/**")
    }
}
