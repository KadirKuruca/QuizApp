package com.kadirkuruca.quizapp.data.model

data class QuizResponse(
    val response_code: Int,
    val results: List<Question>
)