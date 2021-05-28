package com.nusademy.nusademy.dataapi.register

data class GuestTeacherRequest(
    val Description: String,
    val Name: String,
    val Notes: String,
    val Status: String,
    val `class`: String,
    val created_by: String,
    val date_of_teaching: String,
    val id: String,
    val school: String,
    val target_audience: String,
    val time_finished: String,
    val time_start: String,
    val top_talent: String,
    val updated_by: String
)