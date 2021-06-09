package com.nusademy.nusademy.dataapi


import com.google.gson.annotations.SerializedName

class ListDataSpecialization : ArrayList<ListDataSpecialization.ListDataSpecializationItem>(){
    data class ListDataSpecializationItem(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("Name")
        val name: String = "",
        @SerializedName("Category")
        val category: String = "",
        @SerializedName("created_at")
        val createdAt: String = "",
        @SerializedName("updated_at")
        val updatedAt: String = ""
    )
}