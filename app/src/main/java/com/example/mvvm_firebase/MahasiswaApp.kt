package com.example.mvvm_firebase

import android.app.Application
import com.example.mvvm_firebase.di.AppContainer
import com.example.mvvm_firebase.di.MahasiswaContainer

class MahasiswaApp: Application() {
    lateinit var container: MahasiswaContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}