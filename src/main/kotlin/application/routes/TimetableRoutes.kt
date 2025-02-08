package com.today.application.routes

import application.request.TimetableRequest
import com.today.domain.ports.TimeTableRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

fun Route.timetableRoutes() {
    val repository: TimeTableRepository by application.inject()
    route("/timetable") {

        get("/all") {
            repository.findAll()?.let { list ->
                call.respond(list.map { it.toResponse() })
            } ?: call.respondText("No records found")


        }

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

        get("/{standardId?}/division") {
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
                call.respond(list.map { it.toResponse() })
            } ?: call.respondText("No data found")
        }

        post {
            val timetable = call.receive<TimetableRequest>()
        }
    }
}

suspend fun requestSentenceTransform(input: String, huggingFaceURL: String): HttpResponse {
    return HttpClient(CIO).use { client ->

        val response = client.post(huggingFaceURL) {
            val content = TextContent(input, ContentType.Text.Plain)
            setBody(content)
        }
        response
    }
}
