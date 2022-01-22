package com.example.universities.view.fragment.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.universities.R
import com.example.universities.data.retrofit.RetrofitBuilder
import com.example.universities.databinding.FragmentFavoritesBinding
import com.example.universities.databinding.FragmentSearchBinding
import com.example.universities.viewmodel.SearchViewModel
import com.example.universities.viewmodel.factory.SearchFactory


class SearchFragment : Fragment() {
    private companion object{
        const val TAG = "Search Fragment"
    }
    private fun log(message: String) {
        Log.d(TAG, message)
    }
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var searchViewModel :SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log("onCreateView")
        setUpSearchViewModel()
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun setUpSearchViewModel(){
        searchViewModel = ViewModelProvider(
            this,
            SearchFactory(RetrofitBuilder().getApiInterface(requireContext()))
        )[SearchViewModel::class.java]
    }

    override fun onDestroyView() {
        log("onDestroyView")
        super.onDestroyView()
        _binding = null
    }

}