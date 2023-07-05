package com.atb.amphibians.data

import com.atb.amphibians.network.AmphibiansApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}

class DefaultAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
): AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibian> {
        return amphibiansApiService.getAmphibians()
    }
}