package com.nusademy.nusademy.dataapi.register

data class TemporaryTeacherRequest(
    val Name: String,
    val Notes: String,
    val Status: String,
    val `class`: String,
    val created_by: String,
    val durations: Int,
    val expectations_start_teaching: String,
    val id: String,
    val school: String,
    val teacher: String,
    val updated_by: String
)