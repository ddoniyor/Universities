package tj.descwn.universities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.descwn.universities.data.model.Institutions
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.utils.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollegeStudyViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val collegesStudyResponse = MutableLiveData<List<Institutions.Institution>>()
    val collegesStudyBadResponse = MutableLiveData<String>()
    val errorCollegesStudy = MutableLiveData<String>()

    fun getAllColleges(){
        val response = apiInterface.getAllColleges()
        response.enqueue(object : Callback<List<Institutions.Institution>> {
            override fun onResponse(
                call: Call<List<Institutions.Institution>>,
                response: Response<List<Institutions.Institution>>
            ) {
                if (response.isSuccessful){
                    collegesStudyResponse.postValue(response.body())
                }else{
                    val message = ErrorHandler().badResponseHandler(response)
                    collegesStudyBadResponse.postValue(message)
                }
            }

            override fun onFailure(call: Call<List<Institutions.Institution>>, t: Throwable) {
                val message = ErrorHandler().errorResponseHandler(t)
                errorCollegesStudy.postValue(message)
            }
        })
    }
}