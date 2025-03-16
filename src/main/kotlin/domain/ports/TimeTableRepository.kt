package com.today.domain.ports

import com.today.application.response.SubjectResponse
import com.today.domain.entity.Subject
import com.today.domain.entity.Timetable
import org.bson.BsonValue
import org.bson.types.ObjectId

interface TimeTableRepository {

    suspend fun findAll(): List<Timetable>?

    suspend fun findById(objectId: ObjectId): Timetable?

    suspend fun getTimetableForStandard(standardId: String, division: String): List<Timetable>?

    suspend fun getSubjectDetails(subject: String): SubjectResponse

}