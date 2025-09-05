package com.meesam.springshoppingxml.states

sealed class UiState<out T> {
    data class Success<out T>(val data: T) : UiState<T>()
    data class Error(val errorMessage: String? = "") : UiState<Nothing>()
    data object Loading :UiState<Nothing>()
    object Idle : UiState<Nothing>()
}