package com.today.application.routes

import application.response.BaseResponse
import com.today.domain.ports.TimeTableRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

fun Route.timetableRoutes() {
    val repository: TimeTableRepository by application.inject()
    route("v1/timetable") {

        get("/{id?}") {
            val id = call.parameters["id"]
            if (id.isNullOrEmpty()) {
                return@get call.respondText(
                    text = "Missing id",
                    status = HttpStatusCode.BadRequest
                )
            }
            repository.findById(ObjectId(id))?.let {
                call.respond(it.toResponse())
            } ?: call.respondText("No records found for id $id")
        }

        get("/{standardId?}/{division}") {
            val standardId = call.parameters["standardId"]
            val division = call.parameters["division"]
            if (standardId.isNullOrEmpty()) {
                return@get call.respondText(
                    text = "Missing standardId",
                    status = HttpStatusCode.BadRequest
                )
            }
            if (division.isNullOrEmpty()) {
                return@get call.respondText(
                    text = "Missing division",
                    status = HttpStatusCode.BadRequest
                )
            }
            repository.getTimetableForStandard(standardId, division)?.let { list ->
                call.respond(BaseResponse(status = "success", result = list.map { it.toResponse() }))
            } ?: call.respondText("No data found")
        }
    }
}
