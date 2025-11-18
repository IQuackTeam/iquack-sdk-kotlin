// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform) apply false
    alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlinx.serialization) apply false
    `maven-publish`
}

allprojects {
    group = "ru.iquack"
    version = System.getenv("GITHUB_REF_NAME")?.removePrefix("v")?: "1.0.0"
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("kotlin-multiplatform") || plugins.hasPlugin("com.android.library")) {
            publishing {
                publications {
                    create<MavenPublication>("maven") {
                        from(components.findByName("kotlin") ?: return@create)
                        groupId = project.group.toString()
                        artifactId = project.name
                        version = project.version.toString()

                        pom {
                            name.set(project.name)
                            description.set("IQuack SDK module: ${project.name}")
                            url.set("https://github.com/IQuackTeam/iquack-sdk")
                        }
                    }
                }
                repositories {
                    maven {
                        name = "GitHubPackages"
                        url = uri("https://maven.pkg.github.com/IQuackTeam/iquack-sdk")
                        credentials {
                            username = System.getenv("GITHUB_ACTOR")
                            password = System.getenv("GITHUB_TOKEN")
                        }
                    }
                }
            }
        }
    }
}