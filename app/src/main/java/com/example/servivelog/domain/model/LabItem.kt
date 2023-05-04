package com.example.servivelog.domain.model

import com.example.servivelog.data.database.entities.LabEntity


data class LabItem(
    var idL: Int,
    var nombre: String,
    var descripcion: String
)

fun LabEntity.toDomain() = LabItem(idL = idL, nombre = nombre, descripcion = descripcion)
