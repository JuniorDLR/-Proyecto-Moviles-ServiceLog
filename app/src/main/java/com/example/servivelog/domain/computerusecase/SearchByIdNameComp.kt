package com.example.servivelog.domain.computerusecase

import com.example.servivelog.data.ComputerRepository
import com.example.servivelog.domain.model.ComputerItem
import javax.inject.Inject

class SearchByIdNameComp @Inject constructor(
    private val computerRepository: ComputerRepository
) {

    fun searchCompByName(comp: String): ComputerItem{
        return computerRepository.searchComByN(comp)
    }
}