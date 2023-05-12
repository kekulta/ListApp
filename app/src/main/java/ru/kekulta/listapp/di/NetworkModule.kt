package ru.kekulta.listapp.di

import org.koin.dsl.module
import ru.kekulta.listapp.features.list.data.NetworkClient
import ru.kekulta.listapp.features.list.data.network.RetrofitNetworkClient

val networkModule = module {
    single { RetrofitNetworkClient() as NetworkClient }
}