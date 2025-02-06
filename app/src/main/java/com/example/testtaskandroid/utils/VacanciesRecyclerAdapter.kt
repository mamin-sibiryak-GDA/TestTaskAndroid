package com.example.testtaskandroid.utils

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.R
import com.example.testtaskandroid.data.Vacancy
import com.example.testtaskandroid.databinding.ItemVacancyBinding


class VacanciesRecyclerAdapter: ListAdapter<Vacancy, VacanciesRecyclerAdapter.VacanciesRecyclerViewHolder>(
    VacanciesDiffUtilCallback()
) {

    class VacanciesRecyclerViewHolder(val binding: ItemVacancyBinding) : RecyclerView.ViewHolder(binding.root)

    class VacanciesDiffUtilCallback: DiffUtil.ItemCallback<Vacancy>() {
        override fun areItemsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Vacancy, newItem: Vacancy): Boolean {
            return oldItem === newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VacanciesRecyclerViewHolder {
        val inflater = ContextCompat.getSystemService(parent.context, LayoutInflater::class.java) as LayoutInflater
        val binding = ItemVacancyBinding.inflate(inflater, parent, false)
        return VacanciesRecyclerViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VacanciesRecyclerViewHolder, position: Int) {
        val item  = getItem(position)
        if (item.isFavorite)
            holder.binding.buttonIsFavourite.setImageResource(R.drawable.ic_vacancy_liked)
        if (item.lookingNumber != null)
            holder.binding.textHowManyWatching.text = "Сейчас просматривает " + item.lookingNumber.toString() + " " + peopleDeclension(item.lookingNumber)
        else
            holder.binding.textHowManyWatching.visibility = View.GONE
        holder.binding.textVacancyTitle.text = item.title.trim()
        if (item.salary.short != null)
            holder.binding.textVacancySalary.text = item.salary.short
        else
            holder.binding.textVacancySalary.visibility = View.GONE
        holder.binding.textVacancyCity.text = item.address.town
        holder.binding.textCompanyName.text = item.company
        holder.binding.textVacancyExperience.text = item.experience.previewText
        holder.binding.textVacancyPublicationData.text = "Опубликовано " + dateDeclension(item.publishedDate)
        holder.itemView.setOnClickListener {
        }
        holder.binding.buttonIsFavourite.setOnClickListener {
        }
        holder.binding.buttonVacancy.setOnClickListener {
        }
    }
}