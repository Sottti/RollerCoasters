package com.sottti.roller.coasters.data.network

import com.github.michaelbull.result.Result
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel
import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk

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

internal fun clientRequestExceptionWithBodyFailure(failure: Exception): ClientRequestException {
    val response = mockk<HttpResponse>()
    val call = mockk<HttpClientCall>()
    val request = mockk<HttpRequest>()
    every { response.call } returns call
    every { response.status } returns HttpStatusCode.BadRequest
    every { response.headers } returns Headers.Empty
    every { call.request } returns request
    every { request.url } returns Url("https://example.com")
    every { request.method } returns HttpMethod.Get
    coEvery { call.bodyNullable(any()) } throws failure
    return ClientRequestException(response, "Unreadable body")
}
