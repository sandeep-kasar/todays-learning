package application.request

import com.today.domain.entity.Timetable
import org.bson.types.ObjectId

data class TimetableRequest(
    val standard: Int,
    val division: String,
)

fun TimetableRequest.toDomain(): Timetable {
    return Timetable(
        id = ObjectId(),
        standard = standard,
        division = division
    )
}
