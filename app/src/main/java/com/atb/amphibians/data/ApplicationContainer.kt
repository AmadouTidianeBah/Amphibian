package com.atb.amphibians.data

import com.atb.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface ApplicationContainer {
    val amphibiansRepository: AmphibiansRepository
}

@OptIn(ExperimentalSerializationApi::class)
class DefaultApplicationContainer: ApplicationContainer {
    private val baseUrl = " https://android-kotlin-fun-mars-server.appspot.com"
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
    private val retrofitService by lazy {
        retrofit.create(AmphibiansApiService::class.java)
    }
    override val amphibiansRepository: AmphibiansRepository by lazy {
        DefaultAmphibiansRepository(retrofitService)
    }
}