package com.nusademy.nusademy.dataapi


import com.google.gson.annotations.SerializedName

class ListDataVideos : ArrayList<ListDataVideos.ListDataVideosItem>(){
    data class ListDataVideosItem(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("Title")
        val title: String = "",
        @SerializedName("Description")
        val description: String = "",
        @SerializedName("url")
        val url: String = "",
        @SerializedName("Creator")
        val creator: Creator = Creator(),
        @SerializedName("created_at")
        val createdAt: String = "",
        @SerializedName("updated_at")
        val updatedAt: String = "",
        @SerializedName("Category")
        val category: String = ""
    ) {
        data class Creator(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("username")
            val username: String = "",
            @SerializedName("email")
            val email: String = "",
            @SerializedName("provider")
            val provider: String = "",
            @SerializedName("confirmed")
            val confirmed: Boolean = false,
            @SerializedName("blocked")
            val blocked: Any? = null,
            @SerializedName("role")
            val role: Int = 0,
            @SerializedName("created_at")
            val createdAt: String = "",
            @SerializedName("updated_at")
            val updatedAt: String = "",
            @SerializedName("top_talent")
            val topTalent: Any? = null,
            @SerializedName("full_name")
            val fullName: String = "",
            @SerializedName("school")
            val school: Any? = null,
            @SerializedName("teacher")
            val teacher: Int = 0,
            @SerializedName("mbti_result")
            val mbtiResult: Any? = null,
            @SerializedName("assignToRole")
            val assignToRole: Int = 0
        )
    }
}