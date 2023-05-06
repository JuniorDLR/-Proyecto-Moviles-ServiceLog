package com.example.servivelog.domain.model

import com.example.servivelog.data.database.entities.ComputerEntity
import com.example.servivelog.data.database.entities.LabEntity

data class ComputerItem (
    val idC: Int,
    val nombre: String,
    val descripcion: String,
    val marca: String,
    val modelo: String,
    val procesador: String,
    val ram: Int,
    val almacenamiento: Int,
    val serviceTag: String,
    val noInventario: String,
    val ubicacion: String
)

fun ComputerEntity.toDomain() = ComputerItem(idC = idC,
    nombre = nombre,
    descripcion = descripcion,
    marca = marca,
    modelo = modelo,
    procesador = procesador,
    ram = ram,
    almacenamiento = almacenamiento,
    serviceTag = serviceTag,
    noInventario = noInventario,
    ubicacion = ubicacion)