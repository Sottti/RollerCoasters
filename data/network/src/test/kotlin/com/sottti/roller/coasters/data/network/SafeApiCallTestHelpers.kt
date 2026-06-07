package com.sottti.roller.coasters.data.network

import com.github.michaelbull.result.Result
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

internal fun createMockClient(
    engine: MockEngine,
    followRedirects: Boolean = true,
): HttpClient = HttpClient(engine) {
    install(ContentNegotiation) { json() }
    expectSuccess = true
    this.followRedirects = followRedirects
}

internal fun mockEngineForResponse(
    status: HttpStatusCode,
    content: String,
    headers: Headers = Headers.Empty,
): MockEngine =
    MockEngine { request ->
        respond(
            content = content,
            headers = headers,
            status = status,
        )
    }

internal suspend fun HttpClient.fetchWithSafeApiCall(): Result<String, ExceptionApiModel> =
    safeApiCall {
        fetch<String>("https://example.com")
    }
