import com.android.build.api.dsl.LibraryExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.plugin.compose")
}

extensions.configure<LibraryExtension> {
    configureNamespace(project.name)
    compileSdk = Config.compileSdk

    defaultConfig { minSdk = Config.minSdk }

    compileOptions { configureCompileOptions() }

    buildFeatures { compose = true }

    packaging { configurePackaging() }
}

extensions.configure<KotlinAndroidProjectExtension> {
    jvmToolchain(Config.jvmToolchain)
    compilerOptions { configureCompiler() }
}