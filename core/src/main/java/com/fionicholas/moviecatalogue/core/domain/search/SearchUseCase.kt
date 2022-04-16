package com.fionicholas.moviecatalogue.core.domain.search

import com.fionicholas.moviecatalogue.core.domain.search.model.Search
import io.reactivex.Single

interface SearchUseCase {

    fun searchAll(apiKey: String, language: String, query: String): Single<List<Search>>
}