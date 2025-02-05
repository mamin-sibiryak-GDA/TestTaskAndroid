package com.example.testtaskandroid.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testtaskandroid.data.Button
import com.example.testtaskandroid.data.Offer

class SearchViewModel : ViewModel() {
    private val _offers = MutableLiveData<List<Offer>>()
    val users: LiveData<List<Offer>> = _offers

    fun getOffers(){
        val list = mutableListOf<Offer>()
        list.add(
            Offer(
                Button("Text"),
                "1",
                "l1",
                "t1"
            )
        )
        list.add(
            Offer(
                Button("Text"),
                "2",
                "l2",
                "t2"
            )
        )
        list.add(
            Offer(
                Button("Text"),
                "3",
                "l3",
                "t3"
            )
        )
        list.add(
            Offer(
                Button("Text"),
                "4",
                "l4",
                "t4"
            )
        )
        list.add(
            Offer(
                Button("Text"),
                "5",
                "l5",
                "t5"
            )
        )
        _offers.value = list
    }
}