package com.example.testtaskandroid.ui.favourites

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.databinding.FragmentFavouritesBinding
import com.example.testtaskandroid.utils.VacanciesRecyclerAdapter
import com.example.testtaskandroid.utils.vacancyDeclension

class FavouritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavouritesFragment()
    }

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManagerVacanciesFavourites : LinearLayoutManager

    private val favouritesViewModel: FavouritesViewModel by viewModels()

    private val vacanciesRecyclerAdapter by lazy {
        VacanciesRecyclerAdapter()
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManagerVacanciesFavourites = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewVacancyFavourites.apply {
            layoutManager = linearLayoutManagerVacanciesFavourites
            adapter = vacanciesRecyclerAdapter
        }

        favouritesViewModel.getVacanciesFavourites()
        favouritesViewModel.vacancies.observe(viewLifecycleOwner, Observer {
            vacanciesRecyclerAdapter.submitList(it)
        })

        favouritesViewModel.getNumOfVacanciesFavourites()
        favouritesViewModel.numOfVacancies.observe(viewLifecycleOwner, Observer {
            binding.numOfVacanciesFavourites.text = it.toString() + " " + vacancyDeclension(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }
}