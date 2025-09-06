package com.meesam.springshoppingxml.repository.category

import com.meesam.springshoppingxml.models.CategoryResponse
import retrofit2.Response

interface CategoryRepository {

    suspend fun getAllCategories(): Response<List<CategoryResponse>>
}