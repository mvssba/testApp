package br.com.m2silva.domain.repository

import br.com.m2silva.domain.usecase.Customer

interface CustomerRepository {
    suspend fun fetchCustomers(): Result<List<Customer>?>
}