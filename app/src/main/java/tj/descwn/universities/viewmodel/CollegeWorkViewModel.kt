package tj.descwn.universities.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.descwn.universities.data.model.General
import tj.descwn.universities.data.retrofit.ApiInterface
import tj.descwn.universities.utils.ErrorHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollegeWorkViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    val collegesWorkResponse = MutableLiveData<List<General.GeneralItem>>()
    val collegesWorkBadResponse = MutableLiveData<String>()
    val errorCollegesWork = MutableLiveData<String>()

    fun getCollegeProfessions(){
        val response = apiInterface.getCollegeProfessions()
        response.enqueue(object :Callback<List<General.GeneralItem>>{
            override fun onResponse(
                call: Call<List<General.GeneralItem>>,
                response: Response<List<General.GeneralItem>>
            ) {
                if (response.isSuccessful){
                    collegesWorkResponse.postValue(response.body())
                }else{
                    val message = ErrorHandler().badResponseHandler(response)
                    collegesWorkBadResponse.postValue(message)
                }
            }

            override fun onFailure(call: Call<List<General.GeneralItem>>, t: Throwable) {
                val message = ErrorHandler().errorResponseHandler(t)
                errorCollegesWork.postValue(message)
            }

        })
    }
}