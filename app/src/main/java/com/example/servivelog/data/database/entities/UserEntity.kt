package com.example.servivelog.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("tblUser")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("idU") var idU: Int = 0,
    @ColumnInfo("nombre")
    var nombre: String,
    @ColumnInfo("apellido")
    var apellido: String,
    @ColumnInfo("email")
    var correo: String,
    @ColumnInfo("clave")
    var clave: String
)