package com.example.testtaskandroid.ui

import android.view.View

interface ClickListener {
    fun onVacancyClickListener(view: View)
    fun onFavouriteClickListener(pos: Int)
}