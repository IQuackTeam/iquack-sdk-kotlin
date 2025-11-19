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

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            pom {
                name.set("IQuack SDK Core")
                description.set(findProperty("POM_DESCRIPTION") as String)
                url.set(findProperty("POM_URL") as String)
                licenses {
                    license {
                        name.set(findProperty("POM_LICENSE_NAME") as String)
                        url.set(findProperty("POM_LICENSE_URL") as String)
                        distribution.set(findProperty("POM_LICENSE_DIST") as String)
                    }
                }
                scm {
                    url.set(findProperty("POM_SCM_URL") as String)
                    connection.set(findProperty("POM_SCM_CONNECTION") as String)
                    developerConnection.set(findProperty("POM_SCM_DEV_CONNECTION") as String)
                }
                developers {
                    developer {
                        id.set(findProperty("POM_DEVELOPER_ID") as String)
                        name.set(findProperty("POM_DEVELOPER_NAME") as String)
                        url.set(findProperty("POM_DEVELOPER_URL") as String)
                    }
                }
            }
        }
    }
}
