import org.gradle.api.JavaVersion

@Suppress("unused")
object Config {
    const val appId = "com.minroud.mortyverse"

    const val compileSdk = 36
    const val minSdk = 28
    const val targetSdk = 36
    const val versionCode = 1
    const val versionName = "1.0"

    val javaVersion = JavaVersion.VERSION_17
    const val jvmToolchain = 17

    val excludes = listOf("/META-INF/{AL2.0,LGPL2.1}")

    object Plugins {
        const val androidApplication = "mortyverse.android.application"
        const val androidLibrary = "mortyverse.android.library"
        const val jvmLibrary = "mortyverse.jvm.library"
    }
}
