package com.example.universities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.universities.data.model.Institutions
import com.example.universities.data.retrofit.ApiInterface
import com.example.universities.utils.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InstitutionDetailViewModel(private val apiInterface: ApiInterface) : ViewModel()  {
    val institutionDetailResponse = MutableLiveData<Institutions.InstitutionDetail>()
    val institutionDetailBadResponse = MutableLiveData<String>()
    val errorInstitutionDetail = MutableLiveData<String>()

    fun getInstitutionDetail(id:Int){
        val response = apiInterface.getInstitutionDetail(id)
        response.enqueue(object :Callback<Institutions.InstitutionDetail>{
            override fun onResponse(
                call: Call<Institutions.InstitutionDetail>,
                response: Response<Institutions.InstitutionDetail>
            ) {
                if (response.isSuccessful){
                    institutionDetailResponse.postValue(response.body())
                }else{
                    val message = ErrorHandler().badResponseHandler(response)
                    institutionDetailBadResponse.postValue(message)
                }
            }

            override fun onFailure(call: Call<Institutions.InstitutionDetail>, t: Throwable) {
                val message = ErrorHandler().errorResponseHandler(t)
                errorInstitutionDetail.postValue(message)
            }
        })
    }
}