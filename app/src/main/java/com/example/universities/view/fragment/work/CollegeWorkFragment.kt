package com.example.universities.view.fragment.work

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentCollegeWorkBinding
import com.example.universities.databinding.FragmentUniversityStudyBinding
import com.example.universities.viewmodel.CollegeWorkViewModel
import com.example.universities.viewmodel.factory.CollegeWorkFactory

class CollegeWorkFragment : Fragment() {

    private companion object{
        const val TAG = "College Work Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentCollegeWorkBinding? = null
    private val binding get() = _binding!!
    private lateinit var collegeWorkViewModel : CollegeWorkViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpCollegeWorkViewModel()
        _binding = FragmentCollegeWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun setUpCollegeWorkViewModel(){
        collegeWorkViewModel = ViewModelProvider(
            this,
            CollegeWorkFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[CollegeWorkViewModel::class.java]
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}