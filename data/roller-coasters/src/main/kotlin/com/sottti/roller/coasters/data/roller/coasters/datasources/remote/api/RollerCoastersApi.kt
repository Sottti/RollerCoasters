package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.api

import com.sottti.roller.coasters.data.network.fetch
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sottti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoastersApiModel
import io.ktor.client.HttpClient
import javax.inject.Inject

internal class RollerCoastersApi @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getRollerCoasters(
        offset: Int,
        limit: Int,
    ): RollerCoastersApiModel =
        httpClient.fetch("$API_BASE_URL/coasters?offset=$offset&limit=$limit")

    suspend fun getRandomCoaster(): RollerCoasterApiModel =
        httpClient.fetch("$API_BASE_URL/coasters/random")
}