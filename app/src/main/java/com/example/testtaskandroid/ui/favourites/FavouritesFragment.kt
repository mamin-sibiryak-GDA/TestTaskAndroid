package com.example.testtaskandroid.ui.favourites

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.R
import com.example.testtaskandroid.databinding.FragmentFavouritesBinding
import com.example.testtaskandroid.ui.ClickListener
import com.example.testtaskandroid.ui.MainViewModel
import com.example.testtaskandroid.ui.VacanciesRecyclerAdapter
import com.example.testtaskandroid.utils.vacancyDeclension
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritesFragment : Fragment(), ClickListener {

    companion object {
        fun newInstance() = FavouritesFragment()
    }

    private lateinit var listener: ClickListener

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var linearLayoutManagerVacanciesFavourites : LinearLayoutManager

    private val mainViewModel: MainViewModel by activityViewModels()

    private val vacanciesRecyclerAdapter by lazy {
        VacanciesRecyclerAdapter(listener)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManagerVacanciesFavourites = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewVacancyFavourites.apply {
            layoutManager = linearLayoutManagerVacanciesFavourites
            adapter = vacanciesRecyclerAdapter
        }

        mainViewModel.vacancies.observe(viewLifecycleOwner, Observer {
            vacanciesRecyclerAdapter.submitList(it.filter { it.isFavorite })
            binding.numOfVacanciesFavourites.text = (it.filter { it.isFavorite }).size.toString() + " " + vacancyDeclension((it.filter { it.isFavorite }).size)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listener = this
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onVacancyClickListener(view: View) {
        val navController = Navigation.findNavController(view)
        navController.navigate(R.id.navigation_vacancy)
    }

    override fun onFavouriteClickListener(pos: Int) {
        vacanciesRecyclerAdapter.notifyItemChanged(pos)
        mainViewModel.wakeUp()
    }
}