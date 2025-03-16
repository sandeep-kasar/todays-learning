package com.today.domain.entity

import com.today.application.response.SubjectResponse

data class Subject(
    val subject: String? = null,
    val learning: String? = null,
    val homework: String? = null,
    val `key points`: String? = null,
) {
    fun toResponse() = SubjectResponse(
        subject = subject ?: "",
        learning = learning ?: "",
        homework = homework ?: "A",
        `key points`?: ""
    )
}

