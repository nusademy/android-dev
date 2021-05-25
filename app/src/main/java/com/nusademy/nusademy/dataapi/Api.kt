package com.nusademy.nusademy.dataapi

import DataLogin
import DataLogin.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("users/{username}")
    fun getItemUsersDetail(
        @Path("username") username: String
    ): Call<ItemUsersDetail>




    @POST("search/users")
    fun getSearchUsers(
        @Query("q") q: String, @Header("Authorization") token: String
    ): Call<DataLogin>


    @FormUrlEncoded
    @POST("auth/local")
    fun login(
        @Field("identifier") email: String?,
        @Field("password") password: String?,
    ):Call<DataLogin>
}