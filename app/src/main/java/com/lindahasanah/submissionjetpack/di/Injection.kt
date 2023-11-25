package com.lindahasanah.submissionjetpack.di

import com.lindahasanah.submissionjetpack.data.WisataRepository

object Injection {
    fun provideRepository(): WisataRepository {
        return WisataRepository.getInstance()
    }
}