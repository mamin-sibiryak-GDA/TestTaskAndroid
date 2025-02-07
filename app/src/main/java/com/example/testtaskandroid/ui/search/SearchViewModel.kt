package com.example.testtaskandroid.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _isFullSearch = MutableLiveData<Boolean>(false)
    val isFullSearch: LiveData<Boolean> = _isFullSearch

    fun changeIsFullSearchTrue() {
        _isFullSearch.postValue(true)
    }

    fun changeIsFullSearchFalse() {
        _isFullSearch.postValue(false)
    }
}