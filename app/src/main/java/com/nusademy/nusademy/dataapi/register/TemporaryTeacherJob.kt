package com.nusademy.nusademy.dataapi.register

data class TemporaryTeacherJob(
    val Notes: String,
    val Status: String,
    val applicants: List<String>,
    val `class`: String,
    val created_by: String,
    val durations: Int,
    val id: String,
    val name: String,
    val published_at: String,
    val school: String,
    val start_teaching: String,
    val updated_by: String
)