package tj.descwn.universities.data.retrofit

import tj.descwn.universities.data.model.General
import tj.descwn.universities.data.model.Institutions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/v1/institution/all/t")
    fun getAllUniversities(): Call<List<Institutions.Institution>>

    @GET("api/v1/institution/all/f")
    fun getAllColleges(): Call<List<Institutions.Institution>>

    @GET("api/v1/institution/{id}")
    fun getInstitutionDetail(@Path("id") id: Int): Call<Institutions.InstitutionDetail>

    @GET("api/v1/search")
    fun searchInstitutions(@Query("query") query: String): Call<Institutions.InstitutionsSearchList>

    @GET("api/v1/institution/faculty/{id}")
    fun getInstitutionFaculties(@Path("id") id: Int):Call<List<General.GeneralItem>>

    @GET("api/v1/institution/profession/f")
    fun getCollegeProfessions():Call<List<General.GeneralItem>>

    @GET("api/v1/institution/profession/t")
    fun getUniversityProfessions():Call<List<General.GeneralItem>>
}