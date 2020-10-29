import com.android.build.gradle.*

buildscript {

    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Versions.Libs.androidGradlePlugin)
        classpath(Versions.Libs.Kotlin.gradlePlugin)
    }
}

plugins {
    id("de.fayard.buildSrcVersions") version "0.7.0"
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    project.plugins.configure(project = project)
}

fun PluginContainer.configure(project: Project) {
    whenPluginAdded {
        when (this) {
            is AppPlugin -> applyCommonsForPlugin<AppExtension>(project)
            is LibraryPlugin -> applyCommonsForPlugin<LibraryExtension>(project)
        }
    }
}

inline fun <reified T : TestedExtension> applyCommonsForPlugin(project: Project) {
    project.extensions
        .getByType<T>()
        .applyCommons()
}

fun TestedExtension.applyCommons() {
    compileSdkVersion(Versions.Build.compileSdkVersion)
    buildToolsVersion = Versions.Build.buildToolsVersion

    defaultConfig {
        minSdkVersion(Versions.Build.minSdkVersion)
        targetSdkVersion(Versions.Build.targetSdkVersion)
        versionCode = Versions.Build.versionCode
        versionName = Versions.Build.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}