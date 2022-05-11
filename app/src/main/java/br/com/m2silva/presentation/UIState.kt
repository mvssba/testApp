package br.com.m2silva.presentation

import br.com.m2silva.domain.usecase.Customer

data class UIState(
    val loading: Boolean = false,
    val customers: List<Customer> = arrayListOf()
) {
    fun setLoading(status: Boolean) = copy(
        loading = status
    )

    fun setCustomers(list: List<Customer>?) =
        copy(
            loading = false,
            customers = list ?: arrayListOf()
        )
}
