package com.example.universities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.universities.data.model.Institutions
import com.example.universities.data.retrofit.ApiInterface
import com.example.universities.utils.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val institutionSearchResponse = MutableLiveData<Institutions.InstitutionsSearchList>()
    val institutionSearchBadResponse = MutableLiveData<String>()
    val errorInstitutionSearch = MutableLiveData<String>()


    fun searchInstitutions(query:String){
        val response = apiInterface.searchInstitutions(query)
        response.enqueue(object :Callback<Institutions.InstitutionsSearchList>{
            override fun onResponse(
                call: Call<Institutions.InstitutionsSearchList>,
                response: Response<Institutions.InstitutionsSearchList>
            ) {
                if (response.isSuccessful){
                    institutionSearchResponse.postValue(response.body())
                }else{
                    val message = ErrorHandler().badResponseHandler(response)
                    institutionSearchBadResponse.postValue(message)
                }
            }

            override fun onFailure(call: Call<Institutions.InstitutionsSearchList>, t: Throwable) {
                val message = ErrorHandler().errorResponseHandler(t)
                errorInstitutionSearch.postValue(message)
            }

        })
    }

}