package ru.kekulta.listapp

import android.app.Application
import org.koin.core.context.startKoin
import ru.kekulta.listapp.di.interactorModule
import ru.kekulta.listapp.di.networkModule
import ru.kekulta.listapp.di.repositoryModule
import ru.kekulta.listapp.di.viewModelModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(interactorModule, networkModule, repositoryModule, viewModelModule))
        }
    }
}