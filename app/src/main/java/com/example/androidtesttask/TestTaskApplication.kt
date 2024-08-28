package com.example.androidtesttask

import android.app.Application
import com.example.androidtesttask.data.AppContainer
import com.example.androidtesttask.data.AppDataContainer

class TestTaskApplication: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}