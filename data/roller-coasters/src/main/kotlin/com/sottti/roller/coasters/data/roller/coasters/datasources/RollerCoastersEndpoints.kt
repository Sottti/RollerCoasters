package com.sottti.roller.coasters.data.roller.coasters.datasources

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

internal class RollerCoastersEndpoints @Inject constructor(
    private val client: HttpClient
) {
    private val baseUrl = "https://rcdb-api.vercel.app/api"

    suspend fun getRandomCoaster(): RollerCoasterApiModel =
        client.get("$baseUrl/coasters/random") {
            contentType(ContentType.Application.Json)
        }.body()
}