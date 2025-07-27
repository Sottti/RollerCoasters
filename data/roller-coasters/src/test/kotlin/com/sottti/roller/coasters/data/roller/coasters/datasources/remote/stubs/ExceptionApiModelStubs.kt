package com.sottti.roller.coasters.data.roller.coasters.datasources.remote.stubs

import com.sottti.roller.coasters.data.network.model.ExceptionApiModel.NoInternet
import com.sottti.roller.coasters.data.network.model.ExceptionApiModel.ServerError

internal val noInterNetException = NoInternet("No internet")
internal val serverErrorException = ServerError(message = "Server error", code = 503)
