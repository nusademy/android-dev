package com.nusademy.nusademy.dataapi.register

data class Role(
    val created_by: String,
    val description: String,
    val id: String,
    val name: String,
    val permissions: List<String>,
    val type: String,
    val updated_by: String,
    val users: List<String>
)