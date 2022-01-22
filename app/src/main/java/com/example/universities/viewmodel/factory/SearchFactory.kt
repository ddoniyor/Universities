package com.example.universities.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.universities.data.retrofit.ApiInterface
import com.example.universities.viewmodel.SearchViewModel

class SearchFactory (private val apiInterface: ApiInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SearchViewModel::class.java)){
            SearchViewModel(this.apiInterface) as T
        }else{
            throw IllegalArgumentException("Unknown SearchViewModel Class")
        }
    }
}