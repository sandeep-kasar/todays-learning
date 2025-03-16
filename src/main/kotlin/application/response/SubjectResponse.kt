package com.today.application.response


data class SubjectResponse(
    val subject: String,
    val learning: String,
    val homework: String,
    val `key points`: String,
)

data class BaseResponse(
    val status: String,
    val result: SubjectResponse,
)