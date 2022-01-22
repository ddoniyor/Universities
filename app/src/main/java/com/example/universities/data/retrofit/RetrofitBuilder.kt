package com.example.universities.data.retrofit

import android.content.Context
import com.example.universities.App
import com.example.universities.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private lateinit var apiInterface: ApiInterface

    fun getApiInterface(context: Context): ApiInterface {
        if (!::apiInterface.isInitialized) {

            val retrofit = Retrofit.Builder()
                .baseUrl(App.resourses.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())

                .build()
            apiInterface = retrofit.create(ApiInterface::class.java)
        }
        return apiInterface
    }


}