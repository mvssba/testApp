package br.com.m2silva

import android.app.Application
import br.com.m2silva.di.CustomerModule
import br.com.m2silva.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomerAplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CustomerAplication)

            CustomerModule.load()
            NetworkModule.load()
        }
    }
}