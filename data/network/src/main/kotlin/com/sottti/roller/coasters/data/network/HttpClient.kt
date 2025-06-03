package com.sottti.roller.coasters.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal fun createHttpClient() =
    HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                coerceInputValues = true
                explicitNulls = false
                ignoreUnknownKeys = true
                isLenient = true
                prettyPrint = true
            },
            )
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.ANDROID
        }
    }