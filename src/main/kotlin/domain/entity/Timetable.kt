package com.today.domain.entity

import application.response.TimetableResponse
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Timetable(
    @BsonId
    val id: ObjectId,
    val time: String? = null,
    val subject: String? = null,
    val `class`: Int? = null ,
    val standard: Int? = null,
    val division: String? = null,
) {
    fun toResponse() = TimetableResponse(
        id = id.toString(),
        time = time ?: "",
        subject = subject ?: "",
        standard = standard ?: 1,
        division = division ?: "A",
    )
}

