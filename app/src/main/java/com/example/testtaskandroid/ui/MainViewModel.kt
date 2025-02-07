package com.example.testtaskandroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskandroid.data.entities.Offer
import com.example.testtaskandroid.data.repository.OffersVacanciesRepository
import com.example.testtaskandroid.data.entities.Vacancy
import kotlinx.coroutines.launch

open class MainViewModel : ViewModel() {

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> = _offers

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> = _vacancies

    private val offersVacanciesRepository by lazy {
        OffersVacanciesRepository()
    }

    fun getOffers() {
        viewModelScope.launch {
            val response = offersVacanciesRepository.getOffers()
            _offers.postValue(response)
        }
    }

    fun getVacancies() {
        viewModelScope.launch {
            val response = offersVacanciesRepository.getVacancies()
            _vacancies.postValue(response)
        }
    }

    fun wakeUp() {
        _vacancies.postValue(_vacancies.value)
    }
}