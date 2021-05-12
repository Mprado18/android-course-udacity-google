package com.example.android.dessertpusher

import android.app.Application
import timber.log.Timber

//subclasse para app, será utilizada com a lib timber, deve ser adicionada ao manifest
class PusherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}