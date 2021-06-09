package com.nusademy.nusademy.dataapi

import DataLogin
import com.nusademy.nusademy.dataapi.ListDataGuestRequest.ListDataGuestRequestItem
import com.nusademy.nusademy.dataapi.ListdataTemporaryRequest.ListdataTemporaryRequestItem
import com.nusademy.nusademy.dataapi.register.RegisterPostResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    // GET ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("teachers/{id}")
    fun getProfileTeacher(
        @Path("id") id: String,@Header("Authorization") token: String
    ): Call<DataTeacher>



    @GET("users/{id}")
    fun getProfileBasicUser(
        @Path("id") id: String,@Header("Authorization") token: String
    ): Call<DataBasicUser>

    @GET("classes")
    fun getClasses(
        @Query("school.id_contains") id: String,
        @Query("_sort") sort: String,
        @Header("Authorization") token: String
    ): Call<ListDataClasses>

    @GET("spesialitations")
    fun getSpecialization(
        @Header("Authorization") token: String
    ): Call<ListDataSpecialization>

    @GET("guest-teacher-requests")
    fun getGuestRequest(
        @Header("Authorization") token: String,
        @Query("CreatedBy_contains") createdby: String,
        @Query("top_talent.id_contains") iduser: String,
        @Query("Status_contains") status: String,
    ): Call<ListDataGuestRequest>

    @GET("guest-teacher-requests/{id}")
    fun getDetailGuestRequest(
        @Header("Authorization") token: String,
        @Path("id") idguest:String
    ): Call<ListDataGuestRequestItem>

    @GET("temporary-teacher-requests")
    fun getTempRequest(
        @Header("Authorization") token: String,
        @Query("CreatedBy_contains") createdby: String,
        @Query("teacher.id_contains") iduser: String,
        @Query("Status_contains") status: String,
    ): Call<ListdataTemporaryRequest>

    @GET("temporary-teacher-requests/{id}")
    fun getDetailTempRequest(
        @Header("Authorization") token: String,
        @Path("id") idtemp:String
    ): Call<ListdataTemporaryRequestItem>

    @GET("narration-videos")
    fun getVideoNarration(
       @Header("Authorization") token: String
    ): Call<ListDataVideos>


    @GET("schools")
    fun getSearchSchool(
        @Query("name_contains") name: String,@Header("Authorization") token: String
    ): Call<ListDataSchool>

    @GET("schools/{id}")
    fun getProfileSchool(
        @Path("id") id: String,@Header("Authorization") token: String
    ): Call<DataProfileSchool>

    // PUT ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FormUrlEncoded
    @PUT("teachers/{id}")
    fun editProfileTeachers(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Field("last_education") lastEducation: String?,
        @Field("campus") campus: String?,
        @Field("major") major: String?,
        @Field("ipk") ipk: String?,
        @Field("spesialitation") specialization: String?,
        @Field("short_brief") brief: String?,
        @Field("video_branding") videoBranding: String?,
        @Field("linkedin") linkedin: String?,
    ):Call<DataTeacher>

    @FormUrlEncoded
    @PUT("users/{id}")
    fun editProfileUsers(
            @Path("id") id: String,
            @Header("Authorization") token: String,
            @Field("full_name") fullName: String?,
            @Field("email") email: String?,
    ):Call<DataBasicUser>

    @FormUrlEncoded
    @PUT("users/{id}")
    fun editProfileTeachers2(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Field("full_name") fullName: String?,
        @Field("email") email: String?,
    ):Call<DataTeacher>

    @FormUrlEncoded
    @PUT("guest-teacher-requests/{id}")
    fun editGuestRequest(
        @Header("Authorization") token: String,
        @Field("Status") status: String?,
        @Path("id") idrequest: String?,
    ):Call<ListDataGuestRequestItem>

    @FormUrlEncoded
    @PUT("temporary-teacher-requests/{id}")
    fun editTempRequest(
        @Header("Authorization") token: String,
        @Field("Status") status: String?,
        @Path("id") idrequest: String?,
    ):Call<ListdataTemporaryRequestItem>

    @FormUrlEncoded
    @PUT("users/{id}")
    fun editregister(
        @Header("Authorization") token: String,
        @Field("role") role: String?,
        @Path("id") iduser: String?,
    ):Call<DataUser>

    // POST //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FormUrlEncoded
    @POST("auth/local")
    fun login(
        @Field("identifier") email: String?,
        @Field("password") password: String?,
    ):Call<DataLogin>

    @FormUrlEncoded
    @POST("auth/local/register")
    fun register(
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("full_name") fullname: String?,
        @Field("password") password: String?,
        @Field("confirmed") confirm: String?,
        @Field("assignToRole") assignrole: String?
    ):Call<DataLogin>

    @FormUrlEncoded
    @POST("teachers")
    fun addProfileTeachers(
        @Header("Authorization") token: String,
        @Field("last_education") lastEducation: String?,
        @Field("campus") campus: String?,
        @Field("major") major: String?,
        @Field("ipk") ipk: String?,
        @Field("spesialitation") specialization: String?,
        @Field("short_brief") brief: String?,
        @Field("video_branding") videoBranding: String?,
        @Field("linkedin") linkedin: String?,
        @Field("domicilie") iddomicilie: String?,
        @Field("user") iduser: String?,
    ):Call<DataTeacher>

    @FormUrlEncoded
    @POST("guest-teacher-requests")
    fun addGuestRequest(
        @Header("Authorization") token: String,
        @Field("Name") name: String?,
        @Field("Description") description: String?,
        @Field("date_of_teaching") dateteach: String?,
        @Field("time_start") timestart: String?,
        @Field("time_finished") timeend: String?,
        @Field("Notes") notes: String?,
        @Field("top_talent") iduser: String?,
        @Field("school") idschool: String?,
        @Field("class") idclass: String?,
        @Field("target_audience") target: String?,
        @Field("Status") status: String?,
        @Field("CreatedBy") createdby: String?,
    ):Call<ListDataGuestRequestItem>

    @FormUrlEncoded
    @POST("temporary-teacher-requests")
    fun addTempRequest(
        @Header("Authorization") token: String,
        @Field("Name") name: String?,
        @Field("durations") durations: String?,
        @Field("expectations_start_teaching") dateteach: String?,
        @Field("class") idclass: String?,
        @Field("teacher") iduser: String?,
        @Field("school") idschool: String?,
        @Field("Status") status: String?,
        @Field("CreatedBy") createdby: String?,
    ):Call<ListdataTemporaryRequestItem>
}

