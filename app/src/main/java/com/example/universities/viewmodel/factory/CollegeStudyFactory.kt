package com.example.universities.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.universities.data.retrofit.ApiInterface
import com.example.universities.viewmodel.CollegeStudyViewModel

class CollegeStudyFactory(private val apiInterface: ApiInterface): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CollegeStudyViewModel::class.java)){
            CollegeStudyViewModel(this.apiInterface) as T
        }else{
            throw IllegalArgumentException("Unknown CollegeStudyViewModel Class")
        }
    }
}