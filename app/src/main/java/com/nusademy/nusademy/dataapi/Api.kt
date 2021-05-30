package com.nusademy.nusademy.dataapi

import DataLogin
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


    // POST //////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FormUrlEncoded
    @POST("auth/local")
    fun login(
        @Field("identifier") email: String?,
        @Field("password") password: String?,
    ):Call<DataLogin>

    /* @FormUrlEncoded
    @POST("auth/local/register")
    fun postRegister(
            @Field("email") email: String,
            @Field("fullName") fullname: String,
            @Field("username") username: String,
            @Field("password") password: String?
    ): Call<RegisterPostResponse>
     */
}

