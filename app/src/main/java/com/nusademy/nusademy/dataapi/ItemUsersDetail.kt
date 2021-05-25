package com.nusademy.nusademy.dataapi

import com.google.gson.annotations.SerializedName

data class ItemUsersDetail(
        val login: String,
        val id: Int,
        @SerializedName("avatar_url")
        val avatar: String,
        @SerializedName("followers_url")
        val followersUrl: String,
        @SerializedName("following_url")
        val followingUrl: String,
        @SerializedName("type")
        val userType: String,
        val name: String,
        val company: String,
        val location: String,
        @SerializedName("public_repos")
        val repository: Int,
        val followers: Int,
        val following: Int
)