package com.meesam.springshoppingxml.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.meesam.springshoppingxml.Adapters.CategoryAdapter
import com.meesam.springshoppingxml.R
import com.meesam.springshoppingxml.databinding.FragmentHomeBinding
import com.meesam.springshoppingxml.models.CategoryResponse
import com.meesam.springshoppingxml.models.LoginRequest
import com.meesam.springshoppingxml.states.UiState
import com.meesam.springshoppingxml.viewModels.AuthViewModel
import com.meesam.springshoppingxml.viewModels.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    val categoryViewModel: CategoryViewModel by viewModels()
    private var categoryList : List<CategoryResponse> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       handleResponse()
        binding.rvCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
        }
    }

    private fun handleResponse() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                categoryViewModel.categories.collect { uiState ->
                    when (uiState) {
                        is UiState.Error -> {
                           // binding.progressBar.visibility = View.GONE
                            Toast.makeText(
                                requireContext(), uiState.errorMessage, Toast.LENGTH_LONG
                            ).show()
                        }
                        is UiState.Idle -> {
                           // binding.progressBar.visibility = View.GONE
                        }
                        is UiState.Loading -> {
                            //binding.progressBar.visibility = View.VISIBLE
                        }
                        is UiState.Success<*> -> {
                            Toast.makeText(
                                requireContext(), uiState.data.toString(), Toast.LENGTH_LONG
                            ).show()
                            categoryList = uiState.data as List<CategoryResponse>
                            binding.rvCategories.adapter = CategoryAdapter(categoryList){clickedItem->
                                categoryViewModel.onCategoryClicked(clickedItem)
                            }
                        }
                    }
                }
            }
        }
    }

}