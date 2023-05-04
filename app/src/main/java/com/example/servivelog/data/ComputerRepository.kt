package com.example.servivelog.data

import com.example.servivelog.data.database.dao.ComputerDao
import com.example.servivelog.data.database.entities.ComputerEntity
import com.example.servivelog.domain.model.ComputerItem
import com.example.servivelog.domain.model.toDomain
import javax.inject.Inject

class ComputerRepository @Inject constructor(
    private val computerDao: ComputerDao
) {
    fun searchComByN(comp: String): ComputerItem{
        val response: ComputerEntity = computerDao.getComputerByServiceTag(comp)
        return response.toDomain()
    }
}