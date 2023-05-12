package ru.kekulta.listapp.di


import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.kekulta.listapp.features.list.domain.presentation.ListViewModel

val viewModelModule = module { viewModel { ListViewModel(get()) } }