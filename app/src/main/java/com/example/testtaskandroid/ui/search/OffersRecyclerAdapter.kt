package com.example.testtaskandroid.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.R
import com.example.testtaskandroid.data.Offer
import com.example.testtaskandroid.databinding.ItemOfferBinding

class OffersRecyclerAdapter: ListAdapter<Offer, OffersRecyclerAdapter.OffersRecyclerViewHolder>(OffersDiffUtilCallback()) {

    class OffersRecyclerViewHolder(val binding: ItemOfferBinding) : RecyclerView.ViewHolder(binding.root)

    class OffersDiffUtilCallback: DiffUtil.ItemCallback<Offer>() {
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem === newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersRecyclerViewHolder {
        val inflater = ContextCompat.getSystemService(parent.context, LayoutInflater::class.java) as LayoutInflater
        val binding = ItemOfferBinding.inflate(inflater, parent, false)
        return OffersRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OffersRecyclerViewHolder, position: Int) {
        val item  = getItem(position)
        if (item.id == "3")
            holder.binding.iconOffer.setImageResource(R.drawable.ic_favourites_24dp)
        holder.binding.textOffer.text = item.title
        holder.binding.textButtonOffer.text = item.button?.text
    }
}