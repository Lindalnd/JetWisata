package com.lindahasanah.submissionjetpack.ui.common

import com.lindahasanah.submissionjetpack.model.Wisata

sealed class UiState<out T: Any?> {

    object Loading : UiState<Nothing>()

    data class Success<out T: Any>(val data: T) : UiState<T>()

    data class Error(val errorMessage: String) : UiState<Nothing>()
}