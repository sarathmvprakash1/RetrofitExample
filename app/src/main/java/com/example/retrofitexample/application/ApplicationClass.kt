package com.example.retrofitexample.application

import android.app.Application
import com.example.retrofitexample.preference.AppPreferences

class ApplicationClass : Application(){
    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}