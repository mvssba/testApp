package br.com.m2silva.domain.usecase

data class Customer(
    val id: String,
    val name: String?,
    val email: String,
    val phone: String?,
    val status: String,
    val profileLink: String?,
    val profileImage: String?
)
