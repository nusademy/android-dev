package com.nusademy.nusademy.dataapi

import DataLogin
import DataLogin.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    // GET ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GET("users/{id}")
    fun getProfileTeacher(
        @Path("id") id: String,@Header("Authorization") token: String
    ): Call<DataTeacher>

    @GET("users/{id}")
    fun getProfileBasicUser(
        @Path("id") id: String,@Header("Authorization") token: String
    ): Call<DataBasicUser>








    // POST //////////////////////////////////////////////////////////////////////////////////////////////////////////


    @FormUrlEncoded
    @POST("auth/local")
    fun login(
        @Field("identifier") email: String?,
        @Field("password") password: String?,
    ):Call<DataLogin>
}