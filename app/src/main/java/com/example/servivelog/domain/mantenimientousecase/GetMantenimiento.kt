package com.example.servivelog.domain.mantenimientousecase

import com.example.servivelog.data.MantenimientoRepository
import com.example.servivelog.domain.model.MantenimientoItem
import javax.inject.Inject

class GetMantenimiento @Inject constructor(
    private  val mantenimientoRepository: MantenimientoRepository
) {

    suspend operator fun invoke(): List<MantenimientoItem>{
        return mantenimientoRepository.getAllMantenimientos()
    }
}