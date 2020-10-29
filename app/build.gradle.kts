import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("org.gradle.RunInstantApp")
}

android {
    defaultConfig {
        applicationId = "com.example.example_app"
    }
    flavorDimensions("experience")
    productFlavors {
        create("instant") {
            versionCode = Versions.Build.versionCodeInstant
        }
        create("installed") {
            versionCode = Versions.Build.versionCode
        }
    }
}

dependencies {
    // Modules
    implementation(project(Modules.core))
    implementation(project(Modules.exampleFeatureCounter))
    implementation(project(Modules.exampleFeatureRandomizer))

    // Android
    implementation(Versions.Libs.Android.material)
    implementation(Versions.Libs.Android.playServicesInstantApp)

    // Navigation
    implementation(Versions.Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Versions.Libs.AndroidX.Navigation.uiKtx)
}

project.afterEvaluate {

    val bundletool = gradleLocalProperties(rootDir).getProperty("bundletool")

    fun outputFileName(variant: com.android.build.gradle.api.ApplicationVariant): String =
        "${project.name}-${variant.flavorName}-${variant.buildType.name}"

    fun instantAppApkPath(variant: com.android.build.gradle.api.ApplicationVariant): String =
        "${project.buildDir}/outputs/splits/${variant.name}/${outputFileName(variant)}.apks"

    fun instantAppAabPath(variant: com.android.build.gradle.api.ApplicationVariant): String =
        "${project.buildDir}/outputs/bundle/${variant.name}/${outputFileName(variant)}.aab"

    android.applicationVariants.forEach { variant ->
        tasks.create<Exec>(
            "assembleSplits${variant.name.capitalize()}"
        ) {
            dependsOn("bundle")
            group = "build"
            workingDir = project.rootDir
            commandLine = listOf(
                "java",
                "-jar", bundletool, "build-apks",
                "--bundle=${instantAppAabPath(variant)}",
                "--output=${instantAppApkPath(variant)}",
                "--connected-device",
                "--ks=${variant.signingConfig?.storeFile}",
                "--ks-pass=pass:${variant.signingConfig?.storePassword}",
                "--ks-key-alias=${variant.signingConfig?.keyAlias}",
                "--key-pass=pass:${variant.signingConfig?.keyPassword}",
                "--adb=${android.adbExecutable}"
            )
            doFirst {
                mkdir("${project.buildDir}/outputs/splits/${variant.name}")
                println("Running bundletool:\n${commandLine.joinToString { " " }}")
            }
            doFirst {
                delete("${project.buildDir}/outputs/splits/${variant.name}")
            }
            doLast {
                println("Creating splits for ${variant.name}")
            }
        }
    }

    android.applicationVariants.forEach { variant ->
        tasks.create<org.gradle.RunInstantApp_gradle.RunInstantApp>(
            "runinstant${variant.name.capitalize()}"
        ) {
            dependsOn("assembleSplits${variant.name.capitalize()}")
            group = "build"
            workingDir = project.rootDir
            androidSdkPath = android.sdkDirectory.path
            apksPath = instantAppApkPath(variant)
        }
    }
}