// Workaround for a known version catalogs bug to be fixed in Gradle 8.1
// https://github.com/gradle/gradle/issues/22797
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `java-library`
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.mortyverse.config)
}

java {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

kotlinter {
    disabledRules = Config.Kotlinter.disabledRules
}

dependencies {
    testImplementation(libs.test.junit)
}
