package com.kadirkuruca.quizapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kadirkuruca.quizapp.R
import com.kadirkuruca.quizapp.data.remote.QuizApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var quizApi: QuizApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}