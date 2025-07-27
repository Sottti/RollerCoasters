package com.sottti.roller.coasters.data.network

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import com.google.common.truth.Truth.assertThat
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

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

        assertThat(result.error).isInstanceOf(ExceptionApiModel.NoInternet::class.java)
        assertThat(result.error.message).isEqualTo(NO_INTERNET_ERROR_MESSAGE)
    }

    @Test
    fun `returns time out error when timeout exception occurs`() = runBlocking {
        val result = safeApiCall<String> { throw SocketTimeoutException(TIMEOUT_ERROR_MESSAGE) }

        assertThat(result.error).isInstanceOf(ExceptionApiModel.Timeout::class.java)
        assertThat(result.error.message).isEqualTo(TIMEOUT_ERROR_MESSAGE)
    }

    @Test
    fun `returns unknown error when an unknown exception occurs`() = runBlocking {
        val unexpectedErrorMessage = "Unexpected Error"
        val result = safeApiCall<String> { throw RuntimeException(unexpectedErrorMessage) }

        assertThat(result.error).isInstanceOf(ExceptionApiModel.Unknown::class.java)
        assertThat(result.error.message).isEqualTo(unexpectedErrorMessage)
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
}
