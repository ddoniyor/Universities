package com.example.universities.view.fragment.study

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentDetailInstitutionBinding
import com.example.universities.viewmodel.InstitutionDetailViewModel
import com.example.universities.viewmodel.factory.InstitutionDetailFactory


class InstitutionDetailFragment : Fragment() {

    private companion object{
        const val TAG = "Institution Detail Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentDetailInstitutionBinding? = null
    private val binding get() = _binding!!
    private lateinit var institutionDetailViewModel :InstitutionDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        setUpInstitutionDetailViewModel()
        _binding = FragmentDetailInstitutionBinding.inflate(inflater, container, false)
        return binding.root
    }

    //TODO: подготовить все к принятию данных с апи и собрать данные по уникам колледжам и профессиям сделать ресерч
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpInstitutionDetailViewModel() {
        institutionDetailViewModel = ViewModelProvider(
            this,
            InstitutionDetailFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[InstitutionDetailViewModel::class.java]
    }
    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
        _binding = null
    }
}