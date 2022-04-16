package com.fionicholas.moviecatalogue.core.data.search.remote

import com.fionicholas.moviecatalogue.core.data.search.model.SearchResponse
import com.fionicholas.moviecatalogue.core.utils.network.WebApi
import io.reactivex.Single

class SearchApi(private val apiClient: SearchApiClient) : WebApi, SearchApiClient {

    override fun searchAll(apiKey: String, language: String, query: String): Single<SearchResponse> {
        return apiClient.searchAll(apiKey, language, query)
    }
}