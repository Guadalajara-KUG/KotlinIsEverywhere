/*
 * This file was generated by the Gradle 'init' task.
 *
 * The settings file is used to specify which projects to include in your build.
 *
 * Detailed information about configuring a multi-project build in Gradle can be found
 * in the user manual at https://docs.gradle.org/5.3/userguide/multi_project_builds.html
 */

rootProject.name = "KotlinIsEverywhere"

pluginManagement {
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin2js") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:${requested.version}")
            }

            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
            }
        }
    }
}

include(":jvm")
include(":js")
include(":html")