package br.com.m2silva.di

import br.com.m2silva.presentation.MainViewModel
import br.com.m2silva.domain.repository.CustomerRepository
import br.com.m2silva.repository.CustomerRepositoryImpl
import br.com.m2silva.domain.usecase.CustomerUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module

object CustomerModule {
    private val module = module {
        viewModel { MainViewModel(usercase = get()) }

        factory { CustomerUseCase(repository = get()) }
        factory<CustomerRepository> { CustomerRepositoryImpl(service = get()) }
    }

    fun load() {
        loadKoinModules(module)
    }
}
