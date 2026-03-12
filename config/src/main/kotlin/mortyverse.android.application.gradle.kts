import com.android.build.api.dsl.ApplicationExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

plugins {
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jmailen.kotlinter")
}

extensions.configure<ApplicationExtension> {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = Config.appId
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
        versionCode = Config.versionCode
        versionName = Config.versionName

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += Config.excludes
        }
    }
}

extensions.configure<KotlinAndroidProjectExtension> {
    jvmToolchain(Config.jvmToolchain)
}
