package com.atb.amphibians.network

import com.atb.amphibians.data.Amphibian
import retrofit2.http.GET

interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}