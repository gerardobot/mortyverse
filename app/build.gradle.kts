// Most kts functions are marked as incubating in the latest Android plugin, though they work fine
@file:Suppress("UnstableApiUsage")

// Workaround for a known version catalogs bug to be fixed in Gradle 8.1
// https://github.com/gradle/gradle/issues/22797
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.app)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.mortyverse.config)
}

android {
    namespace = Config.appId
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

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    
    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }
    
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    
    buildFeatures {
        compose = true
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
    }
    
    packagingOptions {
        resources {
            excludes += Config.excludes
        }
    }
}

kotlinter {
    disabledRules = Config.Kotlinter.disabledRules
}

val installKotlinterPrePushHook by tasks.creating(
    org.jmailen.gradle.kotlinter.tasks.InstallPrePushHookTask::class
)

tasks.preBuild {
    dependsOn("installKotlinterPrePushHook")
}

dependencies {
    implementation(projects.domain)
    implementation(projects.data)
    implementation(projects.framework)

    implementation(libs.koin.compose)

    implementation(libs.compose.activity)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.preview)
    implementation(libs.compose.ui)

    debugImplementation(libs.debug.compose.testManifest)
    debugImplementation(libs.debug.compose.tooling)

    testImplementation(libs.test.junit)
}
