package com.example.servivelog.ui.gestionmantenimiento.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.servivelog.databinding.FragmentAgregarMantenimientoBinding
import com.example.servivelog.domain.model.mantenimiento.MantenimientoItem
import com.example.servivelog.ui.gestionmantenimiento.viewmodel.GestionManteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAgregarMantenimiento : Fragment() {

    private lateinit var agregarMantenimientoBinding: FragmentAgregarMantenimientoBinding
    private val gestionManteViewModel: GestionManteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        agregarMantenimientoBinding = FragmentAgregarMantenimientoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val agregarbtn = agregarMantenimientoBinding.btnAgregar

        agregarbtn.setOnClickListener {
            gestionManteViewModel.insertMantenimiento(enviarDatos())
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }
        return agregarMantenimientoBinding.root
    }

    private fun enviarDatos(): MantenimientoItem {
        val datosL = gestionManteViewModel.buscarlab(agregarMantenimientoBinding.ctvLab.text.toString())
        val datosC = gestionManteViewModel.buscarComp(agregarMantenimientoBinding.ctvServiceTag.text.toString())
        if (datosL == null || datosC == null) {
            Toast.makeText(
                requireContext(),
                "No existe el laboratorio o el la computadora",
                Toast.LENGTH_SHORT
            ).show()
        }
        val tipoMant = confirmarCheckBox()
        return MantenimientoItem(
            datosL.nombre,
            datosC.serviceTag,
            tipoMant,
            agregarMantenimientoBinding.etDescripcion.text.toString()
        )
    }

    private fun confirmarCheckBox(): String {
        val cbExterno = agregarMantenimientoBinding.cbExterna
        val cbInterno = agregarMantenimientoBinding.cbInterna
        val cbCambio = agregarMantenimientoBinding.cbCambio
        var result = ""
        val checkboxes = listOf(cbExterno, cbInterno, cbCambio)
        for (checkbox in checkboxes) {
            if (checkbox.isChecked) {
                result += checkbox.text.toString() + ". "
            }
        }
        return result
    }
}