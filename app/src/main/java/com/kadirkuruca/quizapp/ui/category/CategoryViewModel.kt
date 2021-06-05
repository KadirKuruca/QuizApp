package com.kadirkuruca.quizapp.ui.category

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kadirkuruca.quizapp.data.model.Category
import com.kadirkuruca.quizapp.data.model.CategoryResponse
import com.kadirkuruca.quizapp.repository.QuizRepository
import com.kadirkuruca.quizapp.util.QUESTION_CATEGORY
import com.kadirkuruca.quizapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CategoryViewModel @ViewModelInject constructor(
    private val quizRepository: QuizRepository
) : ViewModel() {

    val categoryList: MutableLiveData<Resource<CategoryResponse>> = MutableLiveData()

    init {
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            categoryList.postValue(Resource.Loading())
            val categoryResponse = quizRepository.getCategories()
            categoryList.postValue(handleCategoryResponse(categoryResponse))
        }
    }

    private fun handleCategoryResponse(response: Response<CategoryResponse>): Resource<CategoryResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                val mixCategory = Category(name = "Classic (Mixed)",id = QUESTION_CATEGORY)
                resultResponse.trivia_categories.add(0, mixCategory)
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
