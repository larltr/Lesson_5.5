package com.angelika.lesson_55

import android.app.Application
import com.angelika.lesson_55.data.remout.RetrofitClient

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var retrofitClient: RetrofitClient
            private set
    }

    override fun onCreate() {
        super.onCreate()
        retrofitClient = RetrofitClient()
    }
}