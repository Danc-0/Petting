package com.danc.petting.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat.getDrawable
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.load.engine.Resource
import com.danc.petting.R
import com.danc.petting.domain.models.Pets
import com.danc.petting.domain.models.PetsItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.pets_items.view.*
import java.util.zip.Inflater

class MainDashAdapter :
    PagingDataAdapter<PetsItem, MainDashAdapter.PetsViewHolder>(DataDifferentiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {
        return PetsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pets_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {
        val singlePet = getItem(position)
        holder.bindPets(singlePet!!)
    }

    class PetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindPets(petsItem: PetsItem) {
            itemView.petName.text = petsItem.name
            itemView.petsDescription.text = petsItem.temperament
            itemView.petsAge.text = petsItem.life_span

            if (itemView.petImage.drawable != null) {
                itemView.imageLoader.visibility = View.VISIBLE
                itemView.petImage.load(petsItem.image.url)
            }

            itemView.addToFav.setOnClickListener {
                Snackbar.make(it, "Added to Favourites", Snackbar.LENGTH_LONG).show()
            }
        }
    }

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