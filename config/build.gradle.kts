plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.4.2")
}

gradlePlugin {
    plugins.register("mortyverse-config") {
        id = "mortyverse-config"
        version = "1.0"
        implementationClass = "ConfigPlugin"
    }
}
