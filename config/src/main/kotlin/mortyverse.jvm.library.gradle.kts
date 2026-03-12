import org.gradle.jvm.toolchain.JavaLanguageVersion

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("org.jmailen.kotlinter")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(Config.jvmToolchain))
    }
}

kotlin {
    jvmToolchain(Config.jvmToolchain)
}
