package com.example.testtaskandroid.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OffersVacanciesRepository {

    val apiService by lazy {
        ApiClient.apiService
    }

    suspend fun getOffers(): List<Offer> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getOffers().execute()
            response.body()?.offers?: listOf()
        }
    }

    suspend fun getVacancies(): List<Vacancy> {
        return withContext(Dispatchers.IO) {
            val response = apiService.getVacancies().execute()
            response.body()?.vacancies?: listOf()
        }
    }
}