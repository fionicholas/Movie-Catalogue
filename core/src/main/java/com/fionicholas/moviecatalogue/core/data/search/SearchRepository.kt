package com.fionicholas.moviecatalogue.core.data.search

import com.fionicholas.moviecatalogue.core.data.search.model.SearchItem
import com.fionicholas.moviecatalogue.core.utils.network.BaseRepository
import io.reactivex.Single

interface SearchRepository : BaseRepository {
    fun searchAll(apiKey: String, language: String, query : String): Single<List<SearchItem>>
}