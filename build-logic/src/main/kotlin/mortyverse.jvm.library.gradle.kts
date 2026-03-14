plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

kotlin {
    jvmToolchain(Config.jvmToolchain)
    compilerOptions { configureCompiler() }
}
