package br.com.m2silva.data.api

import br.com.m2silva.data.response.CustomerResponse
import retrofit2.Response
import retrofit2.http.GET

interface CustomerAPI {
    @GET("service.json")
    suspend fun customers(): Response<CustomerResponse>
}