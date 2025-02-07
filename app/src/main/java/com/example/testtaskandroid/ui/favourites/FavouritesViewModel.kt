package com.example.testtaskandroid.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskandroid.data.Vacancy
import com.example.testtaskandroid.data.OffersVacanciesRepository
import kotlinx.coroutines.launch

class FavouritesViewModel : ViewModel() {

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> = _vacancies

    private val _numOfVacancies = MutableLiveData<Int>()
    val numOfVacancies: LiveData<Int> = _numOfVacancies

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

    fun getVacanciesFavourites() {
        viewModelScope.launch {
            val response = offersVacanciesRepository.getVacancies()
            _vacancies.postValue(getFavourites(response))
            _numOfVacancies.postValue(getFavourites(response).size)
        }
    }
}