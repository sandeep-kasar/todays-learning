package com.today.application.routes

import com.today.application.response.BaseResponse
import com.today.domain.ports.TimeTableRepository
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.subjectRoutes() {
    val repository: TimeTableRepository by application.inject()
    route("v1") {
        get("/{subject}") {
            val subject = call.parameters["subject"]
            if (subject?.isEmpty() == true) {
                return@get call.respondText(
                    text = "Missing subject",
                    status = HttpStatusCode.BadRequest
                )
            }
            repository.getSubjectDetails(subject.orEmpty()).let { data ->
                call.respond(
                    BaseResponse(
                        status = "success",
                        result = data
                    )
                )
            }
        }
    }
}
