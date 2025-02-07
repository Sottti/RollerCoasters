package com.sottti.roller.coasters.data.roller.coasters.datasources.remote

import com.sottti.roller.coasters.data.network.fetch
import io.ktor.client.HttpClient
import javax.inject.Inject

internal class RollerCoastersEndpoints @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getRandomCoaster(): RollerCoasterApiModel =
        httpClient.fetch("$API_BASE_URL/coasters/random")
}