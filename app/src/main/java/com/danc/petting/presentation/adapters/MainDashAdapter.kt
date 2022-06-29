package com.danc.petting.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.danc.petting.R
import com.danc.petting.domain.models.PetsItem
import kotlinx.android.synthetic.main.pets_items.view.*

class MainDashAdapter :
    PagingDataAdapter<PetsItem, MainDashAdapter.PetsViewHolder>(DataDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        return PetsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pets_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        val petsItem = getItem(position)
        holder.itemView.petName.text = petsItem?.name
        holder.itemView.petsDescription.text = petsItem?.temperament
        holder.itemView.petsAge.text = petsItem?.life_span
        if (holder.itemView.petImage.drawable != null) {
            holder.itemView.imageLoader.visibility = View.VISIBLE
            holder.itemView.petImage.load(petsItem?.image?.url)
        }
    }

    class PetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    object DataDifferentiator : DiffUtil.ItemCallback<PetsItem>() {

        override fun areItemsTheSame(oldItem: PetsItem, newItem: PetsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PetsItem, newItem: PetsItem): Boolean {
            return oldItem == newItem
        }
    }

    interface AddToFavourite {
        fun addToFav()
    }
}