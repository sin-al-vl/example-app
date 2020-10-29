package com.example.core.logging

import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import timber.log.Timber

class KoinTimberLogger : Logger(Level.DEBUG) {

    override fun log(level: Level, msg: MESSAGE) {
        when (this.level) {
            Level.DEBUG -> Timber.d(msg)
            Level.INFO -> Timber.i(msg)
            Level.ERROR -> Timber.e(msg)
        }
    }
}