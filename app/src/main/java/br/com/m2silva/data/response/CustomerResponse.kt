package br.com.m2silva.data.response

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    @SerializedName("customers")
    val items: List<CustomerDataResponse>
)