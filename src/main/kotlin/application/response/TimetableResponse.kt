package application.response


data class TimetableResponse(
    val id: String,
    val time: String,
    val subject: String,
    val standard: Int,
    val division: String,
)