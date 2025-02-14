package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api

import com.sottti.roller.coasters.data.network.fetch
import com.sottti.roller.coasters.data.network.model.ResultApiModel
import com.sottti.roller.coasters.data.network.model.safeApiCall
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoastersApiModel
import com.sottti.roller.coasters.domain.model.RollerCoasterId
import io.ktor.client.HttpClient
import javax.inject.Inject

internal class RollerCoastersApiCalls @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getRollerCoasters(
        offset: Int,
        limit: Int,
    ): ResultApiModel<RollerCoastersApiModel> =
        safeApiCall { httpClient.fetch("$API_BASE_URL?offset=$offset&limit=$limit") }

    suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): ResultApiModel<RollerCoasterApiModel> =
        safeApiCall { httpClient.fetch("$API_BASE_URL/${id.value}") }
}