package com.example.universities.utils

import android.util.Log
import com.example.universities.App
import com.example.universities.R
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException


class ErrorHandler {
    companion object{
        const val TAG= "Error Handler Class"
    }


    fun badResponseHandler(response: Response<*>): String {
        var message:String ? = null
        try {
            message = if (response.code() in 502..504) {
                Log.d(TAG,"bad response handler")
                App.resourses.getString(R.string.remote_error_message)
            } else {
                val badResponse = JSONObject(response.errorBody()?.string())
                badResponse.getString("message")
            }
            return message.toString()
        }catch (e:Exception){
            Log.d(TAG, "$e")
        }
        return message.toString()
    }

    fun errorResponseHandler(t:Throwable): String {
        return if (t is IOException) {
            Log.d(TAG,"$t if t")
            App.resourses.getString(R.string.network_error_message)
        }else{
            Log.d(TAG,"$t else t")
            App.resourses.getString(R.string.unknown_error_message)
        }
    }

}