package com.example.universities.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.universities.R
import com.example.universities.databinding.ItemSettingsBinding

class SettingsAdapter(private val callbackInterface: CallBackInterface) :
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {
    var settings = listOf<String>()

    interface CallBackInterface {
        fun settingsPosition(position: Int)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSettingsList(settings: List<String>) {
        this.settings = settings
        notifyDataSetChanged()
    }

    class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemSettingsBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        return SettingsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_settings,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        with(holder){
            binding.itemSettingsText.text = settings[position]
            itemView.setOnClickListener {
                callbackInterface.settingsPosition(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return settings.size
    }
}