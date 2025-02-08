package com.example.testtaskandroid.data.repository

import com.example.testtaskandroid.data.remote.ApiClient
import com.example.testtaskandroid.data.entities.Offer
import com.example.testtaskandroid.data.entities.Vacancy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OffersVacanciesRepository @Inject constructor(){

    private val apiService by lazy {
        ApiClient.apiService
    }

    suspend fun getOffers(): List<Offer> {
        return withContext(Dispatchers.IO) {
            val response = apiService?.getOffers()?.execute()
            response?.body()?.offers?: listOf()
        }
    }

    suspend fun getVacancies(): List<Vacancy> {
        return withContext(Dispatchers.IO) {
            val response = apiService?.getVacancies()?.execute()
            response?.body()?.vacancies?: listOf()
        }
    }
}