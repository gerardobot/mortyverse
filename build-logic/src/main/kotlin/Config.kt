
import com.android.build.api.dsl.CompileOptions
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.Packaging
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinCommonCompilerOptions

@Suppress("ConstPropertyName")
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
}

internal fun CompileOptions.configureCompileOptions() {
    sourceCompatibility = Config.javaVersion
    targetCompatibility = Config.javaVersion
}

internal fun Packaging.configurePackaging() {
    resources.excludes += Config.excludes
}

internal fun LibraryExtension.configureNamespace(projectName: String) {
    namespace = "${Config.appId}.$projectName"
}

internal fun KotlinCommonCompilerOptions.configureCompiler() {
    freeCompilerArgs.addAll(
        "-Xannotation-default-target=param-property",
        "-Xannotation-target-all"
    )
}
