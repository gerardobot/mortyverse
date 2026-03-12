import com.android.build.api.dsl.LibraryExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

plugins {
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jmailen.kotlinter")
}

extensions.configure<LibraryExtension> {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    buildFeatures {
        compose = true
    }
}

extensions.configure<KotlinAndroidProjectExtension> {
    jvmToolchain(Config.jvmToolchain)
}
