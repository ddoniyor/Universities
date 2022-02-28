package com.example.universities.data.model

import com.google.gson.annotations.SerializedName

object General {
    data class GeneralItem(
        @SerializedName("id")
        var id:Int?=null,
        @SerializedName("name")
        var name:String?=null,
        @SerializedName("description")
        var description:String?=null
    )
}