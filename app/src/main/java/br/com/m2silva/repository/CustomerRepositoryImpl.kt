package br.com.m2silva.repository

import br.com.m2silva.data.api.CustomerAPI
import br.com.m2silva.domain.repository.CustomerRepository
import br.com.m2silva.domain.usecase.Customer
import br.com.m2silva.network.handleResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CustomerRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val service: CustomerAPI
) : CustomerRepository {

    override suspend fun fetchCustomers() = withContext(ioDispatcher) {
        try {
            val result = service.customers()
            result.handleResponse().map {
                it?.let {
                    it.items.map { data ->
                        Customer(
                            data.id,
                            data.name,
                            data.email,
                            data.phone,
                            data.status,
                            data.profileLink,
                            data.profileImage
                        )
                    }
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}