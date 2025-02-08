package com.today.infrastructure

import com.mongodb.client.model.Filters.and
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.today.domain.entity.Timetable
import com.today.domain.ports.TimeTableRepository
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import org.bson.conversions.Bson
import org.bson.types.ObjectId

class TimetableRepositoryImpl(
    private val mongoDatabase: MongoDatabase
) : TimeTableRepository {

    companion object {
        const val TIMETABLE_COLLECTION = "timetable"
    }

    override suspend fun findAll(): List<Timetable> {
        return mongoDatabase.getCollection<Timetable>(TIMETABLE_COLLECTION).find().toList()
    }

    override suspend fun findById(objectId: ObjectId): Timetable? =
        mongoDatabase.getCollection<Timetable>(TIMETABLE_COLLECTION).withDocumentClass<Timetable>()
            .find(eq("_id", objectId))
            .firstOrNull()

    override suspend fun getTimetableForStandard(standardId: String, division: String): List<Timetable> {
        val filter: Bson = and(eq("standard", standardId), eq("division", division))
        return mongoDatabase.getCollection<Timetable>(TIMETABLE_COLLECTION).find(filter).toList()
    }

}