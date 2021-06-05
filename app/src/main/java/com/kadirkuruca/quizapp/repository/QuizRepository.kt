package com.kadirkuruca.quizapp.repository

import com.kadirkuruca.quizapp.data.model.CategoryResponse
import com.kadirkuruca.quizapp.data.model.QuizResponse
import com.kadirkuruca.quizapp.data.remote.QuizApi
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizRepository @Inject constructor(
    private val quizApi: QuizApi
) {
    suspend fun getQuestions(category: Int): Response<QuizResponse>{
        return quizApi.getQuestions(category = category)
    }

    suspend fun getCategories(): Response<CategoryResponse>{
        return quizApi.getCategories()
    }
}