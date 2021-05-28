package com.nusademy.nusademy.dataapi.register

import com.google.gson.annotations.SerializedName

data class RegisterPostResponse(
    val blocked: Boolean,
    val confirmed: Boolean,
    @SerializedName("email")
    val email: String = "",
    @SerializedName("full_name")
    val fullName: String = "",
    val guest_teacher_jobs: List<GuestTeacherJob>,
    val guest_teacher_requests: List<GuestTeacherRequest>,
    val id: String,
    val narration_videos: List<NarrationVideo>,
    val provider: String,
    val role: Role,
    val school: School,
    val teacher: Teacher,
    val temporary_teacher_jobs: List<TemporaryTeacherJob>,
    val temporary_teacher_requests: List<TemporaryTeacherRequest>,
    val top_talent: TopTalent,
    @SerializedName("username")
    val username: String = ""
)