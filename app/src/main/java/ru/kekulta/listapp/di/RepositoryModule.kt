package ru.kekulta.listapp.di

import org.koin.dsl.module
import ru.kekulta.listapp.features.list.data.ArtRepositoryImpl
import ru.kekulta.listapp.features.list.domain.api.ArtRepository

val repositoryModule = module {
    single { ArtRepositoryImpl(get())  as ArtRepository}
}