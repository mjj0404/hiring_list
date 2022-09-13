package com.fetchreward.hiring.utility

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class HiringListApplication: Application() {

    override fun onCreate() {
        PreferenceManager.init(this.applicationContext)
        AppCompatDelegate.setDefaultNightMode(PreferenceManager[Constant.THEME_SELECTION])
        super.onCreate()
    }

}