package com.example.universities.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.universities.data.retrofit.ApiInterface
import com.example.universities.viewmodel.CollegeWorkViewModel

class CollegeWorkFactory (private val apiInterface: ApiInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CollegeWorkViewModel::class.java)){
            CollegeWorkViewModel(this.apiInterface) as T
        }else{
            throw IllegalArgumentException("Unknown CollegeWorkViewModel Class")
        }
    }
}