package ru.kekulta.listapp.di

import org.koin.dsl.module
import ru.kekulta.listapp.features.list.domain.api.ArtInteractor
import ru.kekulta.listapp.features.list.domain.impl.ArtInteractorImpl

val interactorModule = module {
    single { ArtInteractorImpl(get()) as ArtInteractor }
}