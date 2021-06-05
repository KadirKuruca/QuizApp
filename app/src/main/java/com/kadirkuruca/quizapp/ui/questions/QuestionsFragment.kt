package com.kadirkuruca.quizapp.ui.questions

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kadirkuruca.quizapp.R
import com.kadirkuruca.quizapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionsFragment : Fragment(R.layout.fragment_questions) {

    private val args: QuestionsFragmentArgs by navArgs()
    private val viewModel: QuestionsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getQuestions(args.category.id)

        viewModel.questionList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    it.data?.let { data ->
                        //TODO : Questions show on the fragment
                    }
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {

                }
            }
        }
    }
}