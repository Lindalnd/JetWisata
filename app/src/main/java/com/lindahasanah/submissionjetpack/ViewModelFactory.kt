package com.lindahasanah.submissionjetpack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lindahasanah.submissionjetpack.data.WisataRepository
import com.lindahasanah.submissionjetpack.ui.screen.detail.DetailViewModel
import com.lindahasanah.submissionjetpack.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: WisataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}