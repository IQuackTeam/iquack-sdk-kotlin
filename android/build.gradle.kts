plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.maven.publish)
}

android {
    namespace = "ru.iquack.sdk.android"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 26

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

group = findProperty("GROUP") as String
version = findProperty("VERSION_NAME") as String

mavenPublishing {
    coordinates(
        groupId = findProperty("GROUP") as String,
        artifactId = "iquack-sdk-android",
        version = findProperty("VERSION_NAME") as String
    )

    pom {
        name.set("IQuack SDK Android")
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

//    // Configure publishing to Maven Central
//    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
//
//    // Enable GPG signing for all publications
//    signAllPublications()
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(project(":core"))
}