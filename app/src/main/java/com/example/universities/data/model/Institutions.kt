package com.example.universities.data.model

import com.google.gson.annotations.SerializedName

object Institutions {
    data class InstitutionsSearchList(
        @SerializedName("data")
        var data: List<Institution>? = null
    )

    data class Institution(
        @SerializedName("id")
        var id:Int?=null,
        @SerializedName("name")
        var name:String?=null,
        @SerializedName("address")
        var address:String?=null,
        @SerializedName("photo_link")
        var photoLink:String?=null
    )

    data class InstitutionDetail(
        @SerializedName("id")
        var id:Int?=null,
        @SerializedName("name")
        var name:String?=null,
        @SerializedName("description")
        var description:String?=null,
        @SerializedName("address")
        var address:String?=null,
        @SerializedName("phone_number")
        var phoneNumber:String?=null,
        @SerializedName("email")
        var email:String?=null,
        @SerializedName("latitude")
        var latitude:String?=null,
        @SerializedName("longitude")
        var longitude:String?=null,
        @SerializedName("web_site")
        var webSite:String?=null
    )
}