package com.sottti.roller.coasters.data.network.model

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import java.net.UnknownHostException

public suspend fun <T> safeApiCall(
    apiCall: suspend () -> T,
): Result<T, ExceptionApiModel> =
    try {
        Ok(apiCall())
    } catch (_: UnknownHostException) { // No Internet
        Err(ExceptionApiModel.NoInternet)
    } catch (_: SocketTimeoutException) { // Timeout
        Err(ExceptionApiModel.Timeout)
    } catch (e: ClientRequestException) { // 4xx errors
        Err(ExceptionApiModel.ClientError(e.response.status.value, e.response.bodyAsText()))
    } catch (e: ServerResponseException) { // 5xx errors
        Err(ExceptionApiModel.ServerError(e.response.status.value))
    } catch (_: Exception) { // Other errors
        Err(ExceptionApiModel.Unknown)
    }