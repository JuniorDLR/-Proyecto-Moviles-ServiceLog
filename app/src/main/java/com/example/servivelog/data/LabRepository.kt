package com.example.servivelog.data

import com.example.servivelog.data.database.dao.LabDao
import com.example.servivelog.data.database.entities.LabEntity
import com.example.servivelog.data.database.entities.MantenimientoEntity
import com.example.servivelog.domain.model.LabItem
import com.example.servivelog.domain.model.toDomain
import javax.inject.Inject

class LabRepository @Inject constructor(
    private var labDao: LabDao
) {
    fun getLabbyLabN(lab: String): LabItem{
        val response: LabEntity = labDao.getLabByLabN(lab)
        return response.toDomain()
    }
}