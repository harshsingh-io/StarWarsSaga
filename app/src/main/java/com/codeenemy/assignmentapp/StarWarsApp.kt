package com.codeenemy.assignmentapp

import android.app.Application
import androidx.multidex.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * [StarWarsApp] is the Application class for the Star Wars Android application.
 */
@HiltAndroidApp
class StarWarsApp : Application() {

    /**
     * Called when the application is starting. Responsible for initializing Timber for logging.
     */
    override fun onCreate() {
        super.onCreate()
        createTimber()
    }

    /**
     * Create Timber for logging, only in DEBUG mode.
     */
    private fun createTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
