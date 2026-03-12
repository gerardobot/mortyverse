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
    implementation(projects.ui)
    implementation(projects.domain)
    implementation(projects.data)
    implementation(projects.framework)

    implementation(platform(libs.compose.bom))
    implementation(platform(libs.koin.bom))

    implementation(libs.compose.activity)
    implementation(libs.koin.android)

    debugImplementation(libs.debug.compose.testManifest)

    testImplementation(libs.test.junit)
}
