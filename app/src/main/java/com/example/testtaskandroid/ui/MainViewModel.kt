package com.example.testtaskandroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskandroid.data.OffersVacanciesRepository
import com.example.testtaskandroid.data.Vacancy
import kotlinx.coroutines.launch

open class MainViewModel : ViewModel() {

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> = _vacancies

    private val _vacanciesFavourites = MutableLiveData<List<Vacancy>>()
    val vacanciesFavourites: LiveData<List<Vacancy>> = _vacanciesFavourites

    private val _numOfVacancies = MutableLiveData<Int>()
    val numOfVacancies: LiveData<Int> = _numOfVacancies

    private val _numOfVacanciesFavourites = MutableLiveData<Int>()
    val numOfVacanciesFavourites: LiveData<Int> = _numOfVacanciesFavourites

    private val offersVacanciesRepository by lazy {
        OffersVacanciesRepository()
    }

    private fun getFavourites(list: List<Vacancy>): List<Vacancy> {
        val favouritesList = emptyList<Vacancy>().toMutableList()
        for (n in list.indices) {
            if (list[n].isFavorite) {
                favouritesList += list[n]
            }
        }
        return favouritesList
    }

    fun getVacancies() {
        viewModelScope.launch {
            val response = offersVacanciesRepository.getVacancies()
            _vacancies.postValue(response)
            _numOfVacancies.postValue(response.size)
        }
    }

    fun getVacanciesFavourites() {
        viewModelScope.launch {
            val response = offersVacanciesRepository.getVacancies()
            _vacanciesFavourites.postValue(getFavourites(response))
            _numOfVacanciesFavourites.postValue(getFavourites(response).size)
        }
    }
}