plugins {
    alias(libs.plugins.android.app)
    id("mortyverse.android.application")
}

android {
    namespace = Config.appId

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

//TODO: INVESTIGATE
tasks.matching { it.name == "installKotlinterPrePushHook" }.configureEach {
    tasks.named("preBuild") {
        dependsOn(this@configureEach)
    }
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
