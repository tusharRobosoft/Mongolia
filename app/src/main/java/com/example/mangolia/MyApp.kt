package com.example.mangolia

import android.app.Application
import com.example.mangolia.di.apiModule
import com.example.mangolia.di.repositoryModule
import com.example.mangolia.di.retrofitModule
import com.example.mangolia.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(apiModule, viewModelModule, repositoryModule, retrofitModule))
        }
    }
}