@file:Suppress("UnstableApiUsage")

// Workaround for a known version catalogs bug to be fixed in Gradle 8.1
// https://github.com/gradle/gradle/issues/22797
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.mortyverse.config)
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        consumerProguardFiles("consumer-rules.pro")
    }

    compileOptions {
        sourceCompatibility = Config.javaVersion
        targetCompatibility = Config.javaVersion
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Config.kotlinCompilerExtensionVersion
    }

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }

    buildFeatures {
        compose = true
    }
}

kotlinter {
    disabledRules = Config.Kotlinter.disabledRules
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)

    implementation(libs.coil)
    implementation(libs.coil.gif)

    implementation(libs.compose.ui)

    implementation(libs.moshi)
    implementation(libs.moshi.adapters)
    kapt(libs.kapt.moshi)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    debugImplementation(libs.debug.chucker)
    releaseImplementation(libs.release.chucker)

    testImplementation(libs.test.junit)
}
