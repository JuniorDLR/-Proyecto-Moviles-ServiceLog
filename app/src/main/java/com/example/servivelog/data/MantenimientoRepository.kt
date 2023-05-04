package com.example.servivelog.data

import androidx.annotation.WorkerThread
import com.example.servivelog.data.database.dao.MantenimientoDao
import com.example.servivelog.data.database.entities.MantenimientoEntity
import com.example.servivelog.domain.model.MantenimientoItem
import com.example.servivelog.domain.model.toDomain
import javax.inject.Inject

class MantenimientoRepository @Inject constructor(
    private val mantenimientoDao: MantenimientoDao
) {

    suspend fun getAllMantenimientos(): List<MantenimientoItem>{
        val response: List<MantenimientoEntity> = mantenimientoDao.getAllMaintenances()
        return response.map { it.toDomain() }
    }

    @WorkerThread
    suspend fun insertMantenimiento(mantenimientoEntity: MantenimientoEntity){
        mantenimientoDao.insertMaintenance(mantenimientoEntity)
    }

    @WorkerThread
    suspend fun updateMantenimiento(mantenimientoEntity: MantenimientoEntity){
        mantenimientoDao.updateMaintenance(mantenimientoEntity)
    }

    @WorkerThread
    suspend fun deleteMantenimiento(mantenimientoEntity: MantenimientoEntity){
        mantenimientoDao.deleteMaintenance(mantenimientoEntity)
    }
}