package com.example.testtaskandroid.ui.search

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManagerOffers : LinearLayoutManager
    private lateinit var linearLayoutManagerVacancies : LinearLayoutManager

    private val searchViewModel: SearchViewModel by viewModels()

    private val offersRecyclerAdapter by lazy {
        OffersRecyclerAdapter()
    }

    private val vacanciesRecyclerAdapter by lazy {
        VacanciesRecyclerAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManagerOffers = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerViewOffer.apply {
            layoutManager = linearLayoutManagerOffers
            adapter = offersRecyclerAdapter
        }

        searchViewModel.getOffers()

        searchViewModel.offers.observe(viewLifecycleOwner, Observer {
            offersRecyclerAdapter.submitList(it)
        })



        linearLayoutManagerVacancies = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewVacancy.apply {
            layoutManager = linearLayoutManagerVacancies
            adapter = vacanciesRecyclerAdapter
        }

        searchViewModel.getVacancies()

        searchViewModel.vacancies.observe(viewLifecycleOwner, Observer {
            vacanciesRecyclerAdapter.submitList(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val filterButton = binding.filterButton
        filterButton.setOnClickListener{
            binding.recyclerViewOffer.visibility = View.GONE
        }
        return binding.root
    }
}