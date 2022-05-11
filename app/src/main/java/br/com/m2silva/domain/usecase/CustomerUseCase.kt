package br.com.m2silva.domain.usecase

import br.com.m2silva.domain.repository.CustomerRepository

class CustomerUseCase(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke() =
        repository.fetchCustomers()
}