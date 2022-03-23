package tj.descwn.universities.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tj.descwn.universities.R
import tj.descwn.universities.data.model.Institutions
import tj.descwn.universities.databinding.ItemStudyBinding

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


            if (institution.photoLink != null) {
                Glide.with(itemView)
                    .load(tj.descwn.universities.App.resourses.getString(R.string.base_url_for_photo) + institution.photoLink)
                    .centerCrop()
                    .into(binding.itemStudyImage)
            }else{
                binding.itemStudyImage.setImageResource(R.drawable.ic_baseline_image_not_supported_24)
            }
        }
    }

    override fun getItemCount(): Int {
        return institutions.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}