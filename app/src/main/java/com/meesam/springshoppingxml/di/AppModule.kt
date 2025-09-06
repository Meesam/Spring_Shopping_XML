package com.meesam.springshoppingxml.di

import com.meesam.springshoppingxml.api.AuthApiService
import com.meesam.springshoppingxml.api.AuthInterceptor
import com.meesam.springshoppingxml.api.CategoryApi
import com.meesam.springshoppingxml.repository.auth.AuthRepository
import com.meesam.springshoppingxml.repository.auth.AuthRepositoryImpl
import com.meesam.springshoppingxml.repository.category.CategoryRepository
import com.meesam.springshoppingxml.repository.category.CategoryRepositoryImpl
import com.meesam.springshoppingxml.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit.Builder {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun getAuthApiServices(retrofitBuilder: Retrofit.Builder): AuthApiService {
        return retrofitBuilder.build().create(AuthApiService::class.java)
    }

    @Provides
    @Singleton
    fun getCategoryApiServices(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): CategoryApi {
        return retrofitBuilder.client(okHttpClient).build().create(CategoryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApiService: AuthApiService): AuthRepository {
        return AuthRepositoryImpl(authApiService)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(categoryApi: CategoryApi): CategoryRepository {
        return CategoryRepositoryImpl(categoryApi)
    }
}