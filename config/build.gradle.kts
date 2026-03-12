import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

val con = extensions.getByType<VersionCatalogsExtension>().named("con")

dependencies {
    implementation(con.findLibrary("android-gradle-plugin").get())
    implementation(con.findLibrary("kotlin-gradle-plugin").get())
    implementation(con.findLibrary("compose-compiler-gradle-plugin").get())
    implementation(con.findLibrary("kotlinter-gradle").get())
}
