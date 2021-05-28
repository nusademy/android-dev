package com.nusademy.nusademy.dataapi.register

data class GuestTeacherJob(
    val Description: String,
    val Status: String,
    val applicants: List<String>,
    val `class`: String,
    val created_by: String,
    val date: String,
    val id: String,
    val name: String,
    val school: String,
    val target_audience: String,
    val time_finished: String,
    val time_start: String,
    val updated_by: String
)