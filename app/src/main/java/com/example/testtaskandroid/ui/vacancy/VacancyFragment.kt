package com.example.testtaskandroid.ui.vacancy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testtaskandroid.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VacancyFragment : Fragment() {

    companion object {
        fun newInstance() = VacancyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_vacancy, container, false)
    }
}