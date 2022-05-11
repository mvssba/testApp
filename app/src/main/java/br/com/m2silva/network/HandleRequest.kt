package br.com.m2silva.network

import retrofit2.Response

fun <T> Response<T>.handleResponse(): Result<T?> {
    if (this.isSuccessful) {
        return Result.success(this.body())
    }

    throw Throwable()
}