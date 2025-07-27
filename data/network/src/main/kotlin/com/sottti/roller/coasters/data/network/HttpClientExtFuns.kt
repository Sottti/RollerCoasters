package com.sottti.roller.coasters.data.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

public suspend inline fun <reified T> HttpClient.fetch(
    url: String,
): T = get(url) { contentType(ContentType.Application.Json) }.body()
