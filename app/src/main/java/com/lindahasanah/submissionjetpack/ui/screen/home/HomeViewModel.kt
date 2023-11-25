package com.lindahasanah.submissionjetpack.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lindahasanah.submissionjetpack.data.WisataRepository
import com.lindahasanah.submissionjetpack.model.Wisata
import com.lindahasanah.submissionjetpack.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: WisataRepository): ViewModel(){
    private val _uistate : MutableStateFlow<UiState<List<Wisata>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Wisata>>> get() = _uistate

    fun getAllWisata(){
        viewModelScope.launch {
            repository.getAllWisata()
                .catch {
                    _uistate.value = UiState.Error(it.message.toString())
                }
                .collect { wisata ->
                    _uistate.value = UiState.Success(wisata)
                }
        }
    }

    private val _wisataList: MutableStateFlow<List<Wisata>> = MutableStateFlow(emptyList())
    val wisata: StateFlow<List<Wisata>> get() = _wisataList

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun search(newQuery : String){
        _query.value = newQuery
        _wisataList.value = repository.searchWisata(_query.value)
//            .sortedBy { it.name }
//            .groupBy { it.name[0] }

    }
}