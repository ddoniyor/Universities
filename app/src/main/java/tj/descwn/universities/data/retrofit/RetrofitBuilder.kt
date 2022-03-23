package tj.descwn.universities.data.retrofit

import android.content.Context

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tj.descwn.universities.R

class RetrofitBuilder {
    private lateinit var apiInterface: ApiInterface

    fun getApiInterface(context: Context): ApiInterface {
        if (!::apiInterface.isInitialized) {
            val retrofit = Retrofit.Builder()
                .baseUrl(tj.descwn.universities.App.resourses.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())

                .build()
            apiInterface = retrofit.create(ApiInterface::class.java)
        }
        return apiInterface
    }


}