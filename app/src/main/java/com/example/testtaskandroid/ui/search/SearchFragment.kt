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

    private lateinit var linearLayoutManager : LinearLayoutManager

    private val searchViewModel: SearchViewModel by viewModels()

    private val offersRecyclerAdapter by lazy {
        OffersRecyclerAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerViewOffer.apply {
            layoutManager = linearLayoutManager
            adapter = offersRecyclerAdapter
        }

        searchViewModel.getOffers()

        searchViewModel.offers.observe(viewLifecycleOwner, Observer {
            offersRecyclerAdapter.submitList(it)
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
        }
        return binding.root
    }
}