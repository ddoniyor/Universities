package tj.descwn.universities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.descwn.universities.data.model.Institutions
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.utils.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UniversityStudyViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val universitiesStudyResponse = MutableLiveData<List<Institutions.Institution>>()
    val universitiesStudyBadResponse = MutableLiveData<String>()
    val errorUniversitiesStudy = MutableLiveData<String>()

    fun getAllUniversities(){
        val response = apiInterface.getAllUniversities()
        response.enqueue(object :Callback<List<Institutions.Institution>>{
            override fun onResponse(
                call: Call<List<Institutions.Institution>>,
                response: Response<List<Institutions.Institution>>
            ) {
                if (response.isSuccessful){
                    universitiesStudyResponse.postValue(response.body())
                }else{
                    val message = ErrorHandler().badResponseHandler(response)
                    universitiesStudyBadResponse.postValue(message)
                }
            }

            override fun onFailure(call: Call<List<Institutions.Institution>>, t: Throwable) {
               val message = ErrorHandler().errorResponseHandler(t)
                errorUniversitiesStudy.postValue(message)
            }
        })
    }

}