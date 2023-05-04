package com.example.servivelog.domain.mantenimientousecase

import com.example.servivelog.data.MantenimientoRepository
import com.example.servivelog.data.database.entities.toInsertDatabase
import com.example.servivelog.data.database.entities.toUDDatabase
import com.example.servivelog.domain.model.MantenimientoItem
import javax.inject.Inject

class CUDMantenimiento @Inject constructor(
    private val mantenimientoRepository: MantenimientoRepository
) {

    suspend fun insertMantenimiento(mantenimientoItem: MantenimientoItem){
        mantenimientoRepository.insertMantenimiento(mantenimientoItem.toInsertDatabase())//Mapper especializado para insertar
    }

    suspend fun updateMantenimiento(mantenimientoItem: MantenimientoItem){
        mantenimientoRepository.updateMantenimiento(mantenimientoItem.toUDDatabase())//Mapper especialiozado para actualizar
    }

    suspend fun deleteMantenimiento(mantenimientoItem: MantenimientoItem){
        mantenimientoRepository.deleteMantenimiento(mantenimientoItem.toUDDatabase())//Mapper especializado para eliminar
    }
}