package com.example.servivelog.domain.model

import com.example.servivelog.data.database.entities.MantenimientoEntity

data class MantenimientoItem(
    var labname: String,
    var computadora: String,
    var tipoLimpieza: String,
    var desc: String
)

fun MantenimientoEntity.toDomain() = MantenimientoItem(labname = labname, computadora = computadora, tipoLimpieza = tipoLimpieza, desc = desc)
