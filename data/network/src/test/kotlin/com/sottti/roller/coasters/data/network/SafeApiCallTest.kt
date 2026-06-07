package com.sottti.roller.coasters.data.network

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respondError
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

internal class SafeApiCallTest {

    @Test
    fun `returns Ok when API call is successful`() = runBlocking {
        val successMessage = "Success"
        val result: Result<String, ExceptionApiModel> = safeApiCall { successMessage }

        assertThat(result.isOk).isTrue()
        assertThat(result.get()).isEqualTo(successMessage)
    }

    @Test
    fun `returns no internet error when unknown host exception occurs`() = runBlocking {
        val result = safeApiCall<String> { throw UnknownHostException(NO_INTERNET_ERROR_MESSAGE) }

        assertThat(result.getError()).isInstanceOf(ExceptionApiModel.NoInternet::class.java)
        assertThat(result.getError()?.message).isEqualTo(NO_INTERNET_ERROR_MESSAGE)
    }

    @Test
    fun `returns time out error when timeout exception occurs`() = runBlocking {
        val result = safeApiCall<String> { throw SocketTimeoutException(TIMEOUT_ERROR_MESSAGE) }

        assertThat(result.getError()).isInstanceOf(ExceptionApiModel.Timeout::class.java)
        assertThat(result.getError()?.message).isEqualTo(TIMEOUT_ERROR_MESSAGE)
    }

    @Test
    fun `returns unknown error when an unknown exception occurs`() = runBlocking {
        val unexpectedErrorMessage = "Unexpected Error"
        val result = safeApiCall<String> { throw RuntimeException(unexpectedErrorMessage) }

        assertThat(result.getError()).isInstanceOf(ExceptionApiModel.Unknown::class.java)
        assertThat(result.getError()?.message).isEqualTo(unexpectedErrorMessage)
    }

    @Test
    fun `returns server error when server response exception occurs`() = runBlocking {
        val content = "Internal Server Error"
        val mockEngine = mockEngineForResponse(
            status = HttpStatusCode.InternalServerError,
            content = content,
        )
        val mockClient = createMockClient(mockEngine)
        val result = mockClient.fetchWithSafeApiCall()
        val error = result.getError()

        assertThat(error).isInstanceOf(ExceptionApiModel.ServerError::class.java)
        with(error as ExceptionApiModel.ServerError) {
            assertThat(code).isEqualTo(500)
        }
    }

    @Test
    fun `production client returns server error when server returns 5xx`() = runBlocking {
        val mockClient = createHttpClient(
            MockEngine {
                respondError(
                    status = HttpStatusCode.InternalServerError,
                    content = "Internal Server Error",
                )
            }
        )

        val result = mockClient.fetchWithSafeApiCall()

        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.ServerError::class.java)
        with(error as ExceptionApiModel.ServerError) {
            assertThat(code).isEqualTo(500)
        }
    }

    @Test
    fun `returns client error when client request exception occurs`() = runBlocking {
        val content = "Resource not found"
        val mockEngine = mockEngineForResponse(
            status = HttpStatusCode.NotFound,
            content = content,
        )
        val mockClient = createMockClient(mockEngine)

        val result = mockClient.fetchWithSafeApiCall()

        val error = result.getError()

        assertThat(error).isInstanceOf(ExceptionApiModel.ClientError::class.java)
        with(error as ExceptionApiModel.ClientError) {
            assertThat(code).isEqualTo(404)
            assertThat(errorBody).isEqualTo(content)
            assertThat(message).contains("404")
        }
    }

    @Test
    fun `production client returns client error when server returns 4xx`() = runBlocking {
        val content = "Resource not found"
        val mockClient = createHttpClient(
            MockEngine {
                respondError(
                    status = HttpStatusCode.NotFound,
                    content = content,
                )
            }
        )

        val result = mockClient.fetchWithSafeApiCall()

        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.ClientError::class.java)
        with(error as ExceptionApiModel.ClientError) {
            assertThat(code).isEqualTo(404)
            assertThat(errorBody).isEqualTo(content)
        }
    }

    @Test
    fun `returns redirect error when redirect response exception occurs`() = runBlocking {
        val mockEngine = mockEngineForResponse(
            content = "",
            headers = headersOf(HttpHeaders.Location, "https://example.com/redirected"),
            status = HttpStatusCode.MovedPermanently,
        )
        val mockClient = createMockClient(
            engine = mockEngine,
            followRedirects = false,
        )

        val result = mockClient.fetchWithSafeApiCall()

        val error = result.getError()
        assertThat(error).isInstanceOf(ExceptionApiModel.RedirectError::class.java)
        with(error as ExceptionApiModel.RedirectError) {
            assertThat(code).isEqualTo(301)
            assertThat(message).contains("301")
        }
    }

    @Test(expected = CancellationException::class)
    fun `rethrows cancellation exception`() {
        runBlocking {
            safeApiCall<String> { throw CancellationException("Job cancelled") }
        }
    }
}
