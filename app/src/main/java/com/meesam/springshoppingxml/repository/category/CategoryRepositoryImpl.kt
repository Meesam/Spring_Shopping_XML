package com.meesam.springshoppingxml.repository.category

import com.meesam.springshoppingxml.api.CategoryApi
import com.meesam.springshoppingxml.models.CategoryResponse
import jakarta.inject.Inject
import retrofit2.Response

class CategoryRepositoryImpl @Inject constructor(private val categoryApi: CategoryApi):
    CategoryRepository {
    override suspend fun getAllCategories(): Response<List<CategoryResponse>> {
        return categoryApi.getAllCategory()
    }
}