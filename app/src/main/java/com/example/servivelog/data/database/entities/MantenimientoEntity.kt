package com.example.servivelog.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("tblMantenimiento")
data class MantenimientoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("idM")
    var idM: Int,
    @ColumnInfo("lab")
    var labname: String,
    @ColumnInfo("comp")
    var computadora: String,
    @ColumnInfo("tipoLimpieza")
    var tipoLimpieza: String,
    @ColumnInfo("descripcion")
    var desc: String
)
