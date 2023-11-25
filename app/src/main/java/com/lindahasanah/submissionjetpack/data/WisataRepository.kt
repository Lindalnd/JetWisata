package com.lindahasanah.submissionjetpack.data

import com.lindahasanah.submissionjetpack.model.Wisata
import com.lindahasanah.submissionjetpack.model.WisataData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class WisataRepository {

    private val wisatadetail = mutableListOf<Wisata>()

    fun getAllWisata(): Flow<List<Wisata>> {
        return flowOf(wisatadetail)
    }
    fun searchWisata(query: String): List<Wisata>{
        return wisatadetail.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    fun getWisataById(wisataId: String): Wisata {
        return WisataData.wisata.first {
            it.id == wisataId
        }
    }

    companion object {
        @Volatile
        private var instance: WisataRepository? = null

        fun getInstance(): WisataRepository =
            instance ?: synchronized(this) {
                WisataRepository().apply {
                    instance = this
                }
            }
    }
}