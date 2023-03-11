import org.gradle.api.JavaVersion

@Suppress("unused")
object Config {
    const val appId = "com.minroud.mortyverse"

    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    val javaVersion = JavaVersion.VERSION_11
    const val jvmTarget = "11"

    const val kotlinCompilerExtensionVersion = "1.4.3"

    val excludes = listOf("/META-INF/{AL2.0,LGPL2.1}")

    object Kotlinter {
        val disabledRules = arrayOf("no-wildcard-imports")
    }
}
