package com.fetchreward.hiring.utility

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.fetchreward.hiring.R

class PreferenceManager() {

    constructor(context: Context) : this() {
        context.getSharedPreferences(
            Constant.PACKAGE_NAME,
            Context.MODE_PRIVATE)
    }

    companion object {
        private var instance: PreferenceManager? = null

        fun init(context: Context) {
            if (instance == null) {
                instance = PreferenceManager(context)
            }
        }

        fun getInstance(): PreferenceManager? {
            if (instance == null) {
                throw IllegalStateException()
            }
            return instance
        }
    }



}