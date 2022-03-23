package tj.descwn.universities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.descwn.universities.data.model.General
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.utils.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UniversityWorkViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val universitiesWorkResponse = MutableLiveData<List<General.GeneralItem>>()
    val universitiesWorkBadResponse = MutableLiveData<String>()
    val errorUniversitiesWork = MutableLiveData<String>()

    fun getUniversityProfessions(){
        val response = apiInterface.getUniversityProfessions()
        response.enqueue(object : Callback<List<General.GeneralItem>> {
            override fun onResponse(
                call: Call<List<General.GeneralItem>>,
                response: Response<List<General.GeneralItem>>
            ) {
                if (response.isSuccessful){
                    universitiesWorkResponse.postValue(response.body())
                }else{
                    val message = ErrorHandler().badResponseHandler(response)
                    universitiesWorkBadResponse.postValue(message)
                }
            }

            override fun onFailure(call: Call<List<General.GeneralItem>>, t: Throwable) {
                val message = ErrorHandler().errorResponseHandler(t)
                errorUniversitiesWork.postValue(message)
            }

        })
    }
}