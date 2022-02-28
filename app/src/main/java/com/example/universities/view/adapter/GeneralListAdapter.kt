package com.example.universities.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.universities.R
import com.example.universities.data.model.General
import com.example.universities.databinding.ItemGeneralBinding


class GeneralListAdapter : RecyclerView.Adapter<GeneralListAdapter.GeneralListViewHolder>() {
    private var generalItems  = listOf<General.GeneralItem>()
    val TAG = "General List Adapter"

    @SuppressLint("NotifyDataSetChanged")
    fun setGeneralItems(generalItems:List<General.GeneralItem>){
        this.generalItems = generalItems
        notifyDataSetChanged()
    }

    class GeneralListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemGeneralBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralListViewHolder {
        return GeneralListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_general, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GeneralListViewHolder, position: Int) {
        val generalItem = generalItems[position]

        with(holder){
            binding.itemGeneralTitle.text = generalItem.name
            if (generalItem.description!=null){
                binding.itemGeneralDescription.visibility = View.VISIBLE
                binding.itemGeneralDescription.text = generalItem.description
            }
        }
    }

    override fun getItemCount(): Int {
        return generalItems.size
    }
}