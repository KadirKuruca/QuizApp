package com.kadirkuruca.quizapp.ui.questions

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirkuruca.quizapp.data.model.QuizResponse
import com.kadirkuruca.quizapp.repository.QuizRepository
import com.kadirkuruca.quizapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class QuestionsViewModel @ViewModelInject constructor(
    private val quizRepository: QuizRepository
):ViewModel() {

    val questionList: MutableLiveData<Resource<QuizResponse>> = MutableLiveData()

    fun getQuestions(categoryId : Int){
        viewModelScope.launch {
            questionList.postValue(Resource.Loading())
            val questionResponse = quizRepository.getQuestions(category = categoryId)
            questionList.postValue(handleQuestionsResponse(questionResponse))
        }
    }

    fun handleQuestionsResponse(response: Response<QuizResponse>): Resource<QuizResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}