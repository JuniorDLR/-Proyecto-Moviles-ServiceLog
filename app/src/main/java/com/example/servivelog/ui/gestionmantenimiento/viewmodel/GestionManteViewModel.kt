package com.example.servivelog.ui.gestionmantenimiento.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servivelog.domain.computerusecase.SearchByIdNameComp
import com.example.servivelog.domain.labusecase.SearchIdNameLab
import com.example.servivelog.domain.mantenimientousecase.CUDMantenimiento
import com.example.servivelog.domain.mantenimientousecase.GetMantenimiento
import com.example.servivelog.domain.model.ComputerItem
import com.example.servivelog.domain.model.LabItem
import com.example.servivelog.domain.model.MantenimientoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GestionManteViewModel @Inject constructor(
    private val getMantenimiento: GetMantenimiento,
    private val cudMantenimiento: CUDMantenimiento,
    private val searchIdNameLab: SearchIdNameLab,
    private val searchByIdNameComp: SearchByIdNameComp
): ViewModel(){
    val modeloMantenimiento = MutableLiveData<List<MantenimientoItem>>()
    var loading = MutableLiveData<Boolean>()
    val mantenimientoItem = MantenimientoItem(" ",  "Sin datos", " ", " ")
    var modeloLab = MutableLiveData<LabItem>()

    fun onCreate(){
        //Funcion para un futuro
        viewModelScope.launch {
            loading.postValue(true)
            var resultado = getMantenimiento()

            if(!resultado.isEmpty()){
                modeloMantenimiento.postValue(resultado)
                loading.postValue(false)
            }else{
                resultado = listOf(mantenimientoItem)
                modeloMantenimiento.postValue(resultado)
                loading.postValue(false)
            }
        }
    }

    fun buscarlab(lab: String): LabItem {
        return searchIdNameLab.searchLabByN(lab)
    }

    fun buscarComp(comp: String): ComputerItem{
        return searchByIdNameComp.searchCompByName(comp)
    }
    fun insertMantenimiento(mantenimientoItem: MantenimientoItem){
        viewModelScope.launch { cudMantenimiento.insertMantenimiento(mantenimientoItem) }
    }
}