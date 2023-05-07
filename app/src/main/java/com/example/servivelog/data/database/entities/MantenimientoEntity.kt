package com.example.servivelog.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.servivelog.domain.model.mantenimiento.MantenimientoCUDItem
import com.example.servivelog.domain.model.mantenimiento.MantenimientoItem

@Entity("tblMantenimiento")
data class MantenimientoEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("idM") var idM: Int = 0,
    @ColumnInfo("lab")
    var labname: String,
    @ColumnInfo("comp")
    var computadora: String,
    @ColumnInfo("tipoLimpieza")
    var tipoLimpieza: String,
    @ColumnInfo("descripcion")
    var desc: String
)

fun MantenimientoItem.toInsertDatabase() = MantenimientoEntity(labname = labname, computadora = computadora, tipoLimpieza = tipoLimpieza, desc = desc)
fun MantenimientoCUDItem.toDatabase() = MantenimientoEntity(idM = idM, labname = labname, computadora = computadora, tipoLimpieza = tipoLimpieza, desc = desc)