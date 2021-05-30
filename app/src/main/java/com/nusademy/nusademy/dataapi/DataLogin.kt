import com.google.gson.annotations.SerializedName
data class DataLogin(
    @SerializedName("jwt")
    val jwt: String,
    @SerializedName("user")
    val user: User
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
    @SerializedName("guest_teacher_jobs")
    val guestTeacherJobs: List<GuestTeacherJob>,
    @SerializedName("guest_teacher_requests")
    val guestTeacherRequests: List<GuestTeacherRequest>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mbti_result")
    val mbtiResult: Any?,
    @SerializedName("narration_videos")
    val narrationVideos: List<Any>,
    @SerializedName("provider")
    val provider: String,
    @SerializedName("role")
    val role: Role,
    @SerializedName("school")
    val school: Any?,
    @SerializedName("teacher")
    val teacher: Teacher,
    @SerializedName("temporary_teacher_jobs")
    val temporaryTeacherJobs: List<TemporaryTeacherJob>,
    @SerializedName("temporary_teacher_requests")
    val temporaryTeacherRequests: List<TemporaryTeacherRequest>,
    @SerializedName("top_talent")
    val topTalent: Any?,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("username")
    val username: String
)

data class GuestTeacherJob(
    @SerializedName("class")
    val classX: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("school")
    val school: Int,
    @SerializedName("Status")
    val status: String,
    @SerializedName("target_audience")
    val targetAudience: String,
    @SerializedName("time_finished")
    val timeFinished: String,
    @SerializedName("time_start")
    val timeStart: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class GuestTeacherRequest(
    @SerializedName("class")
    val classX: Any?,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("date_of_teaching")
    val dateOfTeaching: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Notes")
    val notes: String,
    @SerializedName("school")
    val school: Int,
    @SerializedName("Status")
    val status: String,
    @SerializedName("target_audience")
    val targetAudience: String,
    @SerializedName("time_finished")
    val timeFinished: String,
    @SerializedName("time_start")
    val timeStart: String,
    @SerializedName("top_talent")
    val topTalent: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class Role(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)

data class Teacher(
    @SerializedName("campus")
    val campus: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("domicilie")
    val domicilie: Int,
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
    val spesialitation: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user")
    val user: Int,
    @SerializedName("video_branding")
    val videoBranding: String
)

data class TemporaryTeacherJob(
    @SerializedName("class")
    val classX: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("durations")
    val durations: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("Notes")
    val notes: String,
    @SerializedName("school")
    val school: Int,
    @SerializedName("start_teaching")
    val startTeaching: String,
    @SerializedName("Status")
    val status: String,
    @SerializedName("updated_at")
    val updatedAt: String
)

data class TemporaryTeacherRequest(
    @SerializedName("class")
    val classX: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("durations")
    val durations: Int,
    @SerializedName("expectations_start_teaching")
    val expectationsStartTeaching: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Notes")
    val notes: Any?,
    @SerializedName("school")
    val school: Int,
    @SerializedName("Status")
    val status: String,
    @SerializedName("teacher")
    val teacher: Int,
    @SerializedName("top_talent")
    val topTalent: Any?,
    @SerializedName("updated_at")
    val updatedAt: String
)