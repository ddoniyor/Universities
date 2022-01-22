package com.example.universities.view.fragment.study

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentCollegeStudyBinding
import com.example.universities.databinding.FragmentSearchBinding
import com.example.universities.databinding.FragmentSettingsBinding
import com.example.universities.viewmodel.CollegeStudyViewModel
import com.example.universities.viewmodel.SearchViewModel
import com.example.universities.viewmodel.factory.CollegeStudyFactory


class CollegeStudyFragment : Fragment() {

    private companion object{
        const val TAG = "College Study Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }

    private var _binding: FragmentCollegeStudyBinding? = null
    private val binding get() = _binding!!
    private lateinit var collegeStudyViewModel: CollegeStudyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        setUpCollegeStudyViewModel()
        _binding = FragmentCollegeStudyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun setUpCollegeStudyViewModel(){
        collegeStudyViewModel = ViewModelProvider(
            this,
            CollegeStudyFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[CollegeStudyViewModel::class.java]
    }
    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
        _binding = null
    }


}