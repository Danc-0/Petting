package com.danc.petting.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danc.petting.R
import com.danc.petting.domain.models.Pets
import com.danc.petting.domain.models.PetsItem
import java.util.zip.Inflater

class MainDashAdapter(recentTransactionsList: MutableList<Pets?>) :
    RecyclerView.Adapter<MainDashAdapter.PetsViewHolder>() {

    var petsItem: List<PetsItem>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsViewHolder {

        return PetsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pets_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PetsViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return petsItem!!.size
    }

    inner class PetsViewHolder(binding: View) :
        RecyclerView.ViewHolder(binding) {


        fun bindingPets(petsItem: PetsItem) {

        }
    }

    init {
        recentTransactionsList.clear()
        recentTransactionsList.addAll(recentTransactionsList)
        notifyDataSetChanged()
    }
}