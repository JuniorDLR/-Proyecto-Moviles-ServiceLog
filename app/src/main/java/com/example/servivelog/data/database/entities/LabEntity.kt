package com.example.servivelog.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tblLaboratorio")
data class LabEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("idLab")
    var idL: Int,
    @ColumnInfo("nombreLab")
    var nombre: String,
    @ColumnInfo("descripcion")
    var descripcion: String
)
