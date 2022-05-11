package br.com.m2silva

import br.com.m2silva.compose.Customer
import br.com.m2silva.data.response.CustomerDataResponse
import br.com.m2silva.data.response.CustomerResponse

object CustomerHelper {
    private val customer = Customer(
        id = "1234", name = "Marcos",
        email = "m2silva@hotmail.com", phone = null,
        status = "active"
    )

    val customerItems = listOf(customer)

    private val customerDataResponse = CustomerDataResponse(
        id = "1234", name = "Marcos",
        email = "m2silva@hotmail.com", phone = null,
        status = "active", profileImage = null, profileLink = null
    )

    val customerResponse = CustomerResponse(
        items = listOf(
            customerDataResponse
        )
    )
}