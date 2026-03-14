plugins {
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ktlint)
}

ktlint {
    android.set(true)
    outputToConsole.set(true)

    filter {
        exclude("**/build/**")
        exclude("**/generated/**")
        include("**/*.kt")
    }
}
