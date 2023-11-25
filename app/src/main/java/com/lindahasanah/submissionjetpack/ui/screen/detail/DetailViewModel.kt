package com.lindahasanah.submissionjetpack.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindahasanah.submissionjetpack.data.WisataRepository
import com.lindahasanah.submissionjetpack.model.Wisata
import com.lindahasanah.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: WisataRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<Wisata>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<Wisata>>
        get() = _uiState

    fun getWisataById(wisataId: String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getWisataById(wisataId))
        }
    }
}