package com.meesam.springshoppingxml.api

import com.meesam.springshoppingxml.models.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApi {
    @GET("category/all")
    suspend fun getAllCategory(): Response<List<CategoryResponse>>
}