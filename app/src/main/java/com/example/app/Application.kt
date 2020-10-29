package com.example.app

import android.app.Application
import com.example.core.logging.KoinTimberLogger
import com.example.app.di.mainModule
import com.example.app.di.navigationModule
import com.example.feature_counter.di.counterModule
import com.example.feature_randomizer.di.randomizerModule
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogging()
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                logger(KoinTimberLogger())
            }
            modules(
                listOf(
                    navigationModule,
                    mainModule,
                    counterModule,
                    randomizerModule
                )
            )
        }
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}