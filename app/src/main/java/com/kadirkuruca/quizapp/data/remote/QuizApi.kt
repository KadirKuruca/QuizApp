package com.kadirkuruca.quizapp.data.remote

import com.kadirkuruca.quizapp.data.model.QuizResponse
import com.kadirkuruca.quizapp.util.QUESTION_AMOUNT
import com.kadirkuruca.quizapp.util.QUESTION_CATEGORY
import com.kadirkuruca.quizapp.util.QUESTION_DIFFICULTY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = QUESTION_AMOUNT,
        @Query("category") category: Int = QUESTION_CATEGORY,
        @Query("difficulty") difficulty: String = QUESTION_DIFFICULTY
    ): Response<QuizResponse>
}