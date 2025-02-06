package com.example.testtaskandroid.ui.search

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskandroid.R
import com.example.testtaskandroid.data.Offer
import com.example.testtaskandroid.databinding.ItemOfferBinding


@Suppress("DEPRECATION")
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
        when (item.id) {
            "near_vacancies" -> holder.binding.iconOffer.setImageResource(R.drawable.ic_offer_near_vacancies)
            "level_up_resume" -> holder.binding.iconOffer.setImageResource(R.drawable.ic_offer_level_up_resume)
            "temporary_job" -> holder.binding.iconOffer.setImageResource(R.drawable.ic_offer_temporary_job)

        }
        holder.binding.textOffer.text = item.title.trim()
        holder.binding.textButtonOffer.text = item.button?.text?.trim()
        holder.itemView.setOnClickListener{
            val link = Uri.parse(item.link.trim())
            val intent = Intent(Intent.ACTION_VIEW, link)
            startActivity(holder.binding.root.context, intent, null)
        }
    }
}