package com.example.universities.view.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.universities.App
import com.example.universities.R
import com.example.universities.data.model.Institutions
import com.example.universities.databinding.ItemStudyBinding
import kotlin.math.log

class InstitutionsListAdapter(private val callbackInterface: CallBackInterface) :
    RecyclerView.Adapter<InstitutionsListAdapter.InstitutionsListViewHolder>() {
    val TAG = "Institutions List Adapter"
    private var institutions = listOf<Institutions.Institution>()

    interface CallBackInterface {
        fun goToInstitutionsDetail(id: Int)
    }

    class InstitutionsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemStudyBinding.bind(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setInstitutions(institutions: List<Institutions.Institution>) {
        this.institutions = institutions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstitutionsListViewHolder {
        return InstitutionsListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_study, parent, false)
        )
    }

    override fun onBindViewHolder(holder: InstitutionsListViewHolder, position: Int) {
        val institution = institutions[position]
        with(holder) {
            binding.itemStudyImage.clipToOutline = true

            itemView.setOnClickListener {
                callbackInterface.goToInstitutionsDetail(institution.id!!)
            }
            binding.itemStudyName.text = institution.name
            binding.itemStudyAddress.text = institution.address

            Log.d(TAG, "${institution.photoLink} institution.photoLink")

            if (institution.photoLink != null) {
                Glide.with(itemView)
                    .load(App.resourses.getString(R.string.base_url_for_photo) + institution.photoLink)
                    .centerCrop()
                    .into(binding.itemStudyImage)
            }
        }
    }

    override fun getItemCount(): Int {
        return institutions.size
    }


}