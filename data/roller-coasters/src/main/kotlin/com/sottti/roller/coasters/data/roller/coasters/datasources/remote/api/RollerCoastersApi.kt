package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api

import com.sottti.roller.coasters.data.network.fetch
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoastersApiModel
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import io.ktor.client.HttpClient
import javax.inject.Inject

internal class RollerCoastersApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getRollerCoasters(
        offset: Int,
        limit: Int,
    ): RollerCoastersApiModel =
        httpClient.fetch("$API_BASE_URL?offset=$offset&limit=$limit")

    suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): RollerCoasterApiModel =
        httpClient.fetch("$API_BASE_URL/${id.value}")
}