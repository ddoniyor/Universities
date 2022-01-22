package com.example.universities.view.fragment.study

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentDetailInstitutionBinding
import com.example.universities.databinding.FragmentUniversityStudyBinding
import com.example.universities.viewmodel.UniversityStudyViewModel
import com.example.universities.viewmodel.factory.UniversityStudyFactory

class UniversityStudyFragment : Fragment() {
    private companion object{
        const val TAG = "University Study Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    private var _binding: FragmentUniversityStudyBinding? = null
    private val binding get() = _binding!!
    private lateinit var universityStudyViewModel: UniversityStudyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        setUpUniversityStudyViewModel()
        _binding = FragmentUniversityStudyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun setUpUniversityStudyViewModel(){
        universityStudyViewModel = ViewModelProvider(
            this,
            UniversityStudyFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[UniversityStudyViewModel::class.java]
    }
    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
        _binding = null
    }

}