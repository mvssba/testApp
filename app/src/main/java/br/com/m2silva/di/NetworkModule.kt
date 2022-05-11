package br.com.m2silva.di

import br.com.m2silva.data.api.CustomerAPI
import br.com.m2silva.network.interceptor.NoConnectionInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private val network = module {
        factory { NoConnectionInterceptor(context = get()) }
        factory { providerOkHttpClient(connectivityInterceptor = get()) }
        single { providerRetrofit(okHttpClient = get()) }
        factory { providerService(get()) }
    }

    private fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/newloran2/testApp/main/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun providerOkHttpClient(connectivityInterceptor: NoConnectionInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(connectivityInterceptor)
            .build()
    }

    private fun providerService(retrofit: Retrofit) = retrofit.create(CustomerAPI::class.java)

    fun load() {
        loadKoinModules(network)
    }
}