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
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.R
import com.example.testtaskandroid.databinding.FragmentSearchBinding
import com.example.testtaskandroid.ui.ClickListener
import com.example.testtaskandroid.ui.MainViewModel
import com.example.testtaskandroid.ui.OffersRecyclerAdapter
import com.example.testtaskandroid.ui.VacanciesRecyclerAdapter
import com.example.testtaskandroid.utils.vacancyDeclension

class SearchFragment : Fragment(), ClickListener {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var listener: ClickListener

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManagerOffers: LinearLayoutManager
    private lateinit var linearLayoutManagerVacancies: LinearLayoutManager

    private val searchViewModel: SearchViewModel by viewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private val offersRecyclerAdapter by lazy {
        OffersRecyclerAdapter()
    }

    private val vacanciesRecyclerAdapter by lazy {
        VacanciesRecyclerAdapter(listener)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearLayoutManagerOffers = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.recyclerViewOffer.apply {
            layoutManager = linearLayoutManagerOffers
            adapter = offersRecyclerAdapter
        }

        mainViewModel.offers.observe(viewLifecycleOwner, Observer {
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
            binding.buttonMoreVacancies.text =
                "Ещe " + (it.size - 3).toString() + " " + vacancyDeclension((it.size - 3))
            binding.numOfVacancies.text = it.size.toString() + " " + vacancyDeclension(it.size)
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

                val params =
                    binding.recyclerViewVacancy.layoutParams as ConstraintLayout.LayoutParams
                params.topToBottom = binding.vacancyForYou.id

                binding.buttonMoreVacancies.visibility = View.VISIBLE
                binding.vacancyForYou.visibility = View.VISIBLE
                binding.recyclerViewOffer.visibility = View.VISIBLE
            } else {
                vacanciesRecyclerAdapter.submitList(mainViewModel.vacancies.value)

                binding.buttonMoreVacancies.visibility = View.GONE
                binding.vacancyForYou.visibility = View.GONE
                binding.recyclerViewOffer.visibility = View.GONE

                val params =
                    binding.recyclerViewVacancy.layoutParams as ConstraintLayout.LayoutParams
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
        listener = this
        return binding.root
    }

    override fun onVacancyClickListener(view: View) {
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.navigation_vacancy)
    }

    // Строки с проверкой пропадают при vacanciesRecyclerAdapter.notifyItemChanged(pos)
    // Даже еслм никакие данные не менять всё равно пропадают строки
    // Чем это вызвано без понятия
    override fun onFavouriteClickListener(pos: Int) {
        vacanciesRecyclerAdapter.notifyItemChanged(pos)
        mainViewModel.wakeUp()
    }
}