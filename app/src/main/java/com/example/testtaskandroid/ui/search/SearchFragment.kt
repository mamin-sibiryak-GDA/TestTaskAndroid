package com.example.testtaskandroid.ui.search

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.databinding.FragmentSearchBinding
import com.example.testtaskandroid.ui.MainViewModel
import com.example.testtaskandroid.utils.OffersRecyclerAdapter
import com.example.testtaskandroid.utils.VacanciesRecyclerAdapter
import com.example.testtaskandroid.utils.vacancyDeclension

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManagerOffers : LinearLayoutManager
    private lateinit var linearLayoutManagerVacancies : LinearLayoutManager

    private val searchViewModel: SearchViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val offersRecyclerAdapter by lazy {
        OffersRecyclerAdapter()
    }

    private val vacanciesRecyclerAdapter by lazy {
        VacanciesRecyclerAdapter()
    }

    @SuppressLint("SetTextI18n")
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

        mainViewModel.vacancies.observe(viewLifecycleOwner, Observer {
            if (searchViewModel.isFullSearch.value == false)
                vacanciesRecyclerAdapter.submitList(it.take(3))
            else
                vacanciesRecyclerAdapter.submitList(it)
        })
        mainViewModel.numOfVacancies.observe(viewLifecycleOwner, Observer {
            binding.buttonMoreVacancies.text = "Ещe " + (it-3).toString() + " " + vacancyDeclension((it-3))
            binding.numOfVacancies.text = it.toString() + " " + vacancyDeclension(it)
        })

        binding.buttonMoreVacancies.setOnClickListener {
            searchViewModel.changeIsFullSearchTrue()
        }
        binding.buttonBack.setOnClickListener {
            searchViewModel.changeIsFullSearchFalse()
        }
        searchViewModel.isFullSearch.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                vacanciesRecyclerAdapter.submitList(mainViewModel.vacancies.value?.take(3))

                binding.numOfVacancies.visibility = View.GONE
                binding.imageSort.visibility = View.GONE
                binding.textSort.visibility = View.GONE
                binding.buttonBack.visibility = View.GONE

                val params = binding.recyclerViewVacancy.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = binding.vacancyForYou.id

                binding.buttonMoreVacancies.visibility = View.VISIBLE
                binding.vacancyForYou.visibility = View.VISIBLE
                binding.recyclerViewOffer.visibility = View.VISIBLE
            }
            else {
                vacanciesRecyclerAdapter.submitList(mainViewModel.vacancies.value)

                binding.buttonMoreVacancies.visibility = View.GONE
                binding.vacancyForYou.visibility = View.GONE
                binding.recyclerViewOffer.visibility = View.GONE

                val params = binding.recyclerViewVacancy.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = binding.numOfVacancies.id

                binding.buttonBack.visibility = View.VISIBLE
                binding.numOfVacancies.visibility = View.VISIBLE
                binding.imageSort.visibility = View.VISIBLE
                binding.textSort.visibility = View.VISIBLE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        binding.filterButton.setOnClickListener{
        }
        return binding.root
    }
}