plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

dependencies {
    // Kotlin
    api(Versions.Libs.Kotlin.stdlib)
    api(Versions.Libs.Kotlin.coroutines)
    api(Versions.Libs.AndroidX.ktx)

    // Android
    api(Versions.Libs.Android.playServicesInstantApp)
    api(Versions.Libs.AndroidX.appCompat)
    api(Versions.Libs.AndroidX.Lifecycle.extensions)
    api(Versions.Libs.AndroidX.constraintLayout)
    api(Versions.Libs.AndroidX.Lifecycle.viewModelKtx)

    // Koin
    api(Versions.Libs.Koin.koinAndroidxScope)
    api(Versions.Libs.Koin.koinAndroidxViewModel)

    // Logging
    api(Versions.Libs.Logging.timber)

    // Navigation
    implementation(Versions.Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Versions.Libs.AndroidX.Navigation.uiKtx)
}
