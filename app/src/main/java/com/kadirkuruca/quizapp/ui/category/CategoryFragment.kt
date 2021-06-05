package com.kadirkuruca.quizapp.ui.category

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.kadirkuruca.quizapp.R
import com.kadirkuruca.quizapp.adapter.CategoryAdapter
import com.kadirkuruca.quizapp.data.model.Category
import com.kadirkuruca.quizapp.databinding.FragmentCategoryBinding
import com.kadirkuruca.quizapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_category.*

@AndroidEntryPoint
class CategoryFragment : Fragment(R.layout.fragment_category), CategoryAdapter.OnItemClickListener {

    private val viewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCategoryBinding.bind(view)
        val categoryAdapter = CategoryAdapter(this)

        binding.apply {
            recyclerCategory.apply {
                adapter = categoryAdapter
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
            }
        }

        viewModel.categoryList.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    progressbar.visibility = GONE
                    it.data?.let { categoryResponse ->
                        categoryAdapter.submitList(categoryResponse.trivia_categories)
                    }
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        Toast.makeText(requireContext(), "Error : $message", Toast.LENGTH_SHORT)
                            .show()
                    }
                    progressbar.visibility = GONE
                }
                is Resource.Loading -> {
                    progressbar.visibility = VISIBLE
                }
            }
        }
    }

    override fun onItemClick(category: Category) {

    }
}