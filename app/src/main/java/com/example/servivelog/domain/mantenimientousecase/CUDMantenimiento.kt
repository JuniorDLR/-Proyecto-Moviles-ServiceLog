package com.example.servivelog.domain.mantenimientousecase

import com.example.servivelog.data.MantenimientoRepository
import com.example.servivelog.data.database.entities.toDatabase
import com.example.servivelog.data.database.entities.toInsertDatabase
import com.example.servivelog.domain.model.mantenimiento.MantenimientoCUDItem
import com.example.servivelog.domain.model.mantenimiento.MantenimientoItem
import javax.inject.Inject

class CUDMantenimiento @Inject constructor(
    private val mantenimientoRepository: MantenimientoRepository
) {

    suspend fun insertMantenimiento(mantenimientoItem: MantenimientoItem){
        mantenimientoRepository.insertMantenimiento(mantenimientoItem.toInsertDatabase())//Mapper especializado para insertar
    }

    suspend fun updateMantenimiento(mantenimientoCUDItem: MantenimientoCUDItem){
        mantenimientoRepository.updateMantenimiento(mantenimientoCUDItem.toDatabase())//Mapper especialiozado para actualizar
    }

    suspend fun deleteMantenimiento(mantenimientoCUDItem: MantenimientoCUDItem){
        mantenimientoRepository.deleteMantenimiento(mantenimientoCUDItem.toDatabase())//Mapper especializado para eliminar
    }
}