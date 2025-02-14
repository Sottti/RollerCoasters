package com.sottti.roller.coasters.data.network.model

public sealed class ExceptionApiModel(
    override val message: String,
) : Exception(message) {

    public object NoInternet : ExceptionApiModel("No Internet connection.") {
        @Suppress("unused")
        private fun readResolve(): Any = NoInternet
    }

    public data class ClientError(
        val code: Int,
        val errorBody: String?,
    ) : ExceptionApiModel("Client error: HTTP $code")

    public data class ServerError(
        val code: Int,
    ) : ExceptionApiModel("Server error: HTTP $code")

    public object Timeout : ExceptionApiModel("Request timed out.") {
        @Suppress("unused")
        private fun readResolve(): Any = Timeout
    }

    public data object Unknown : ExceptionApiModel("Unknown error occurred.") {
        @Suppress("unused")
        private fun readResolve(): Any = Unknown
    }
}