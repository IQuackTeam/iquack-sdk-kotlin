import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.android.library)
    id("maven-publish")
}

android {
    namespace = "ru.iquack.sdk"
    compileSdk = 36
    defaultConfig {
        minSdk = 26
    }
}

kotlin {
    androidTarget{
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    jvm()
    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)
        }
    }
}

dependencies {
}

group = findProperty("GROUP") as String
version = findProperty("VERSION_NAME") as String

base {
    archivesName.set("iquack-sdk-kotlin")
}

publishing {
    publications {
        withType<MavenPublication>().configureEach {
            artifactId = "iquack-sdk-kotlin"
        }
    }
    repositories {
        maven {
            name = "ossrh-staging-api"
            url = uri(findProperty("mavenRepositoryUrl") as String)
            credentials {
                username = findProperty("mavenCentralUsername") as String
                password = findProperty("mavenCentralPassword") as String
            }
        }
    }
}
