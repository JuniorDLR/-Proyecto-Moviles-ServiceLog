package com.example.servivelog.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity("tblComputer")
data class ComputerEntity(

    @PrimaryKey(autoGenerate = true) @ColumnInfo("idComp") var idC: Int = 0,
    @ColumnInfo("nombre")
    var nombre: String,
    @ColumnInfo("descripcion")
    var descripcion: String,
    @ColumnInfo("marca")
    var marca: String,
    @ColumnInfo("modelo")
    var modelo: String,
    @ColumnInfo("procesador")
    var procesador: String,
    @ColumnInfo("ram")
    var ram: Int,
    @ColumnInfo("almacenamiento")
    var almacenamiento: Int,
    @ColumnInfo("serviceTag")
    var serviceTag: String,
    @ColumnInfo("noInventario")
    var noInventario: String,
    @ColumnInfo("ubicacion")
    var ubicacion: String, //LABORATORIO
    @ColumnInfo("idL")
    var idL: Int
)
