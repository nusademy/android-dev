package com.nusademy.nusademy.dataapi
import com.google.gson.annotations.SerializedName


data class DataTeacher(
    @SerializedName("campus")
    val campus: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("domicilie")
    val domicilie: Domicilie,
    @SerializedName("id")
    val id: Int,
    @SerializedName("ipk")
    val ipk: Double,
    @SerializedName("last_education")
    val lastEducation: String,
    @SerializedName("linkedin")
    val linkedin: String,
    @SerializedName("major")
    val major: String,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("short_brief")
    val shortBrief: String,
    @SerializedName("spesialitation")
    val spesialitation: Spesialitation,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: User,
    @SerializedName("video_branding")
    val videoBranding: Any?
)

data class Domicilie(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Spesialitation(
    @SerializedName("Category")
    val category: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class User(
    @SerializedName("assignToRole")
    val assignToRole: Any?,
    @SerializedName("blocked")
    val blocked: Boolean,
    @SerializedName("confirmed")
    val confirmed: Boolean,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mbti_result")
    val mbtiResult: Any?,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("role")
    val role: Int,
    @SerializedName("school")
    val school: Any?,
    @SerializedName("teacher")
    val teacher: Int,
    @SerializedName("top_talent")
    val topTalent: Any?,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
)