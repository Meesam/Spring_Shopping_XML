package com.meesam.springshoppingxml.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meesam.springshoppingxml.models.CategoryResponse
import com.meesam.springshoppingxml.repository.category.CategoryRepository
import com.meesam.springshoppingxml.states.UiState
import com.meesam.springshoppingxml.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository):
    ViewModel() {

        private var _categories = MutableStateFlow<UiState<List<CategoryResponse>>>(UiState.Loading)
        val categories: StateFlow<UiState<List<CategoryResponse>>> = _categories.asStateFlow()

        init {
            getAllCategories()
        }

    private fun getAllCategories(){
        viewModelScope.launch {
            _categories.value = UiState.Loading
            val response = categoryRepository.getAllCategories()
            if(response.isSuccessful && response.body() != null){
                _categories.value = UiState.Success(response.body()!!)
            }
            else{
                _categories.value = UiState.Error("Something went wrong to load Categories")
            }
        }
    }

    fun onCategoryClicked(id: Long){
        Log.d(Constants.TAG, id.toString())
    }

}