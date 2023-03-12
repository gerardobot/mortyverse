// Workaround for a known version catalogs bug to be fixed in Gradle 8.1
// https://github.com/gradle/gradle/issues/22797
@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
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

    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
}

kotlinter {
    disabledRules = Config.Kotlinter.disabledRules
}

dependencies {
    implementation(projects.data)

    implementation(libs.moshi)
    implementation(libs.moshi.adapters)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.release)

    testImplementation(libs.test.junit)
}
