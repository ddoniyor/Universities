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
import com.example.universities.databinding.FragmentUniversityWorkBinding
import com.example.universities.viewmodel.UniversityWorkViewModel
import com.example.universities.viewmodel.factory.UniversityWorkFactory


class UniversityWorkFragment : Fragment() {

    private companion object{
        const val TAG = "University Work Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentUniversityWorkBinding? = null
    private val binding get() = _binding!!
    private lateinit var universityWorkViewModel : UniversityWorkViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpUniversityWorkViewModel()
        _binding = FragmentUniversityWorkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun setUpUniversityWorkViewModel(){
        universityWorkViewModel = ViewModelProvider(
            this,
            UniversityWorkFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[UniversityWorkViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}