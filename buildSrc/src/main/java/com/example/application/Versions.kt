object Versions {

    object Build {
        const val compileSdkVersion = 29
        const val buildToolsVersion = "29.0.3"
        const val minSdkVersion = 26
        const val targetSdkVersion = 29
        const val versionCode = 2
        const val versionName = "2"
        const val versionCodeInstant = 1
    }

    object Libs {
        const val androidGradlePlugin = "com.android.tools.build:gradle:3.5.3"

        object Android {
            const val material = "com.google.android.material:material:1.1.0"
            const val playServicesInstantApp =
                "com.google.android.gms:play-services-instantapps:17.0.0"
        }

        object AndroidX {
            const val appCompat = "androidx.appcompat:appcompat:1.1.0"
            const val ktx = "androidx.core:core-ktx:1.2.0"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
            const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"

            object Lifecycle {
                private const val version = "2.2.0"
                const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
                const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            }

            object Navigation {
                private const val version = "2.2.1"
                const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
                const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            }
        }

        object Kotlin {
            private const val version = "1.3.61"
            const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
            const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$version"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.3"
        }

        object Koin {
            private const val koinVersion = "2.0.1"
            const val koinAndroidxScope = "org.koin:koin-androidx-scope:$koinVersion"
            const val koinAndroidxViewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
        }

        object Logging {
            const val timber = "com.jakewharton.timber:timber:4.7.1"
        }
    }
}
