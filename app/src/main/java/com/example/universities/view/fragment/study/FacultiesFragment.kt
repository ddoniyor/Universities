package com.example.universities.view.fragment.study

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentCollegeStudyBinding
import com.example.universities.databinding.FragmentFacultiesBinding
import com.example.universities.viewmodel.InstitutionDetailViewModel
import com.example.universities.viewmodel.factory.InstitutionDetailFactory

class FacultiesFragment : Fragment() {
    private companion object{
        const val TAG = "Faculties Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    private var _binding: FragmentFacultiesBinding? = null
    private val binding get() = _binding!!
    private lateinit var institutionDetailViewModel :InstitutionDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        setUpInstitutionDetailViewModel()
        val institutionId = arguments?.getInt("institutionId")
        log("$institutionId institutionId")
        _binding = FragmentFacultiesBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getInstitutionFaculties()
        getInstitutionFacultiesResponse()


        binding.fragmentFacultiesSwipeRefresh.setOnRefreshListener {
            getInstitutionFaculties()
        }
    }
    private fun getInstitutionFaculties(){
        contentVisibility(false)
    }

    private fun contentVisibility(visibility: Boolean){
        if (visibility){
            binding.fragmentFacultiesRecycler.visibility = View.VISIBLE
            binding.fragmentFacultiesSwipeRefresh.isRefreshing = false
        }else{
            binding.fragmentFacultiesRecycler.visibility = View.GONE
            binding.fragmentFacultiesSwipeRefresh.isRefreshing = true
        }
    }
    private fun getInstitutionFacultiesResponse(){
        with(institutionDetailViewModel){

        }
    }
    private fun setUpInstitutionDetailViewModel() {
        institutionDetailViewModel = ViewModelProvider(
            this,
            InstitutionDetailFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[InstitutionDetailViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}