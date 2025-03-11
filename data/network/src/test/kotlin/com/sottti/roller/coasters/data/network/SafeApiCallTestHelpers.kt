package com.sottti.roller.coasters.data.network

import com.github.michaelbull.result.Result
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

internal fun createMockClient(
    engine: MockEngine,
): HttpClient = HttpClient(engine) {
    install(ContentNegotiation) { json() }
    expectSuccess = true
}

internal fun mockEngineForResponse(
    status: HttpStatusCode,
    content: String,
): MockEngine =
    MockEngine { request ->
        respond(
            content = content,
            status = status,
        )
    }

internal suspend fun HttpClient.fetchWithSafeApiCall(): Result<String, ExceptionApiModel> =
    safeApiCall {
        fetch<String>("https://example.com")
    }