package com.example.servivelog.ui.gestioncomputadora.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servivelog.domain.computerusecase.GetAllComputer
import com.example.servivelog.domain.computerusecase.RUDComputer
import com.example.servivelog.domain.labusecase.RUDLab
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.computer.InsertItem
import com.example.servivelog.domain.model.lab.LabItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GestionCompViewModel @Inject constructor(
    private val getAllComputer: GetAllComputer,
    private val rudComputer: RUDComputer
) : ViewModel() {
    val modeloComputer = MutableLiveData<List<ComputerItem>>()
    val loading = MutableLiveData<Boolean>()
    val computerItem = ComputerItem(
        0,
        "",
        "sin datos",
        "",
        "",
        "",
        0,
        0,
        "",
        "",
        ""
    )

    fun onCreate() {
        viewModelScope.launch {//Se usa una corrutina para conectar el viewmodel
            loading.postValue(true)//permite postear durante la carga
            var resultado = getAllComputer()
            if (!resultado.isEmpty()) {
                modeloComputer.postValue(resultado)
                loading.postValue(false)
            } else {
                resultado = listOf(computerItem)
                modeloComputer.postValue(resultado)
                loading.postValue(false)
            }
        }
    }

    fun insertComputer(computer: InsertItem) {
        viewModelScope.launch {
            rudComputer.insertComputer(computer)
        }
    }

    fun updateComputer(computer: ComputerItem) {
        viewModelScope.launch {
            rudComputer.updateComputer(computer)
        }
    }

    fun deleteComputer(computer: ComputerItem) {
        viewModelScope.launch {
            rudComputer.deleteComputer(computer)
        }
    }

    suspend fun getAllLabs(): List<LabItem> {
        return rudComputer.getallLabs()

    }

}