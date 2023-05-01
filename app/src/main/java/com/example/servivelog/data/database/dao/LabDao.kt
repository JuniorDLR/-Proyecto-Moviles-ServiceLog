package com.example.servivelog.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.servivelog.data.database.entities.LabEntity

@Dao
interface LabDao {

    @Query("SELECT * FROM tblLaboratorio ORDER BY idLab")
    suspend fun getAllLabs(): List<LabEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLab(lab: LabEntity)

    @Update
    suspend fun updateLab(lab: LabEntity)

    @Delete
    suspend fun deleteLab(lab: LabEntity)
}