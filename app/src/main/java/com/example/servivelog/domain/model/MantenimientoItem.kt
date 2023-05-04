package com.example.servivelog.domain.model

import com.example.servivelog.data.database.entities.MantenimientoEntity

data class MantenimientoItem(
    var idL:Int,
    var labname: String,
    var idC: Int,
    var computadora: String,
    var tipoLimpieza: String,
    var desc: String
)

fun MantenimientoEntity.toDomain() = MantenimientoItem(idL = idL, labname = labname, idC = idC, computadora = computadora, tipoLimpieza = tipoLimpieza, desc = desc)
