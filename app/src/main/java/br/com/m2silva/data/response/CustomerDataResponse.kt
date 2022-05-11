package br.com.m2silva.data.response

import com.google.gson.annotations.SerializedName

data class CustomerDataResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("profileLink")
    val profileLink: String?,
    @SerializedName("profileImage")
    val profileImage: String?
)