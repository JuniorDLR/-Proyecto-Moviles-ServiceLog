package com.example.servivelog.domain.labusecase

import com.example.servivelog.data.LabRepository
import com.example.servivelog.domain.model.LabItem
import javax.inject.Inject

class SearchIdNameLab @Inject constructor(
    private val labRepository: LabRepository
) {

    fun searchLabByN(lab: String): LabItem{
        return labRepository.getLabbyLabN(lab)
    }
}