package com.example.testtaskandroid.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskandroid.data.Offer
import com.example.testtaskandroid.data.OffersVacanciesRepository
import com.example.testtaskandroid.data.Vacancy
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> = _offers

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> = _vacancies

    val offersVacanciesRepository by lazy {
        OffersVacanciesRepository()
    }

    fun getOffers(){

        viewModelScope.launch {
            val response = offersVacanciesRepository.getOffers()
            _offers.postValue(response)
        }
    }

    fun getVacancies(){

        viewModelScope.launch {
            val response = offersVacanciesRepository.getVacancies()
            _vacancies.postValue(response)
        }
    }
}