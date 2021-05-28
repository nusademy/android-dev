package com.nusademy.nusademy.dataapi.register

data class School(
    val address: String,
    val classes: List<String>,
    val created_by: String,
    val creator: String,
    val guest_teacher_jobs: List<String>,
    val guest_teacher_requests: List<String>,
    val headmaster: String,
    val id: String,
    val name: String,
    val phone_number: String,
    val temporary_teacher_jobs: List<String>,
    val temporary_teacher_requests: List<String>,
    val updated_by: String,
    val website: String
)