package com.example.servivelog.ui.usuario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servivelog.databinding.FragmentMenuBinding
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.mantenimiento.MantenimientoCUDItem
import com.example.servivelog.ui.usuario.adapterMantenimiento.DashboardM
import com.example.servivelog.ui.usuario.viewmodel.UserViewModel


import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class FragmentMenu : Fragment() {

    private lateinit var menuBinding: FragmentMenuBinding
    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuBinding = FragmentMenuBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        CoroutineScope(Dispatchers.Main).launch {
            val listam = userViewModel.getLastMaintenance()
            val computadora = userViewModel.getallcomputers()
            val todomantenimiento = userViewModel.getAllmantenimiento()
            setdashboardM(listam, computadora)
            val cantidad = obtenerlast4Month(todomantenimiento)
            menuBinding.cantidad.text =
                cantidad.toString()//te devuelve la cantidad de mantenimiento creados  en los ultimos 4 meses
        }
        return menuBinding.root
    }

    private fun setdashboardM(listam: List<MantenimientoCUDItem>, computadora: List<ComputerItem>) {//crea el dashboard
        val recyclerView = menuBinding.ultimosMantenimientos
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter =
            DashboardM(requireActivity(), requireActivity(), listam, menuBinding.root, computadora)//muestra las ultimas computadoras , creadas en los ultmos 4 meses
    }

    private fun obtenerlast4Month(listaM: List<MantenimientoCUDItem>): Int {//fun para obtener los 4 meses
        val formato = SimpleDateFormat("yyyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val fechaActual = Date()
        calendar.time = fechaActual
        calendar.add(Calendar.MONTH, -4)
        val fechaLimite = calendar.time

        var contador = 0

        for (m in listaM) {
            val fecha = formato.parse(m.dia)
            if (fecha.after(fechaLimite) || fecha == fechaLimite)
                contador++
        }

        return contador
    }
}