package com.sotti.roller.coasters.data.roller.coasters.datasources.remote.api

import com.sotti.roller.coasters.data.network.fetch
import com.sotti.roller.coasters.data.network.model.ResultApiModel
import com.sotti.roller.coasters.data.network.safeApiCall
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoasterApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.RollerCoastersApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.SearchCoastersApiModel
import com.sotti.roller.coasters.data.roller.coasters.datasources.remote.model.SearchQueryApiModel
import com.sotti.roller.coasters.domain.roller.coasters.model.RollerCoasterId
import io.ktor.client.HttpClient
import javax.inject.Inject

internal class RollerCoastersApiCalls @Inject constructor(
    private val httpClient: HttpClient,
) {
    suspend fun getRollerCoasters(
        offset: Int,
        limit: Int,
    ): ResultApiModel<RollerCoastersApiModel> =
        safeApiCall { httpClient.fetch("$API_BASE_URL?offset=$offset&limit=$limit") }

    suspend fun getRollerCoaster(
        id: RollerCoasterId,
    ): ResultApiModel<RollerCoasterApiModel> =
        safeApiCall { httpClient.fetch("$API_BASE_URL/${id.value}") }

    suspend fun searchRollerCoasters(
        query: SearchQueryApiModel,
    ): ResultApiModel<SearchCoastersApiModel> =
        safeApiCall { httpClient.fetch("$API_BASE_URL/search?q=${query.value}") }
}
