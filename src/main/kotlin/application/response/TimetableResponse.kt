package application.response

import com.today.domain.entity.Subject


data class TimetableResponse(
    val id: String,
    val time: String,
    val subject: Subject,
    val standard: Int,
    val division: String
)

data class BaseResponse(
    val status: String? = null,
    val result: List<TimetableResponse>? = null,
)