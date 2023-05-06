package com.example.servivelog.ui.gestionmantenimiento.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.servivelog.databinding.FragmentEditMantenimientoBinding
import com.example.servivelog.domain.model.mantenimiento.MantenimientoCUDItem
import com.example.servivelog.ui.gestionmantenimiento.viewmodel.GestionManteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentEditMantenimiento : Fragment() {

    private lateinit var editMantenimientoBinding: FragmentEditMantenimientoBinding
    private lateinit var mantenimientoCUDItem: MantenimientoCUDItem
    private val args: FragmentEditMantenimientoArgs by navArgs()
    private val gestionManteViewModel: GestionManteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        editMantenimientoBinding = FragmentEditMantenimientoBinding.inflate(layoutInflater)
        obteniendoDatos()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val btneditar = editMantenimientoBinding.btnEditar

        btneditar.setOnClickListener {

            val tipoMan = confirmarCheckBox()
            mantenimientoCUDItem.labname = editMantenimientoBinding.ctvLab.text.toString()
            mantenimientoCUDItem.computadora =
                editMantenimientoBinding.ctvServiceTag.text.toString()
            mantenimientoCUDItem.tipoLimpieza = tipoMan
            mantenimientoCUDItem.desc = editMantenimientoBinding.etDescripcion.text.toString()
            gestionManteViewModel.updateMantenimiento(mantenimientoCUDItem)
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }



        return editMantenimientoBinding.root
    }

    private fun obteniendoDatos() {
        mantenimientoCUDItem = args.mantenimiento

        val cb = mantenimientoCUDItem.tipoLimpieza.split(". ")

        editMantenimientoBinding.ctvLab.setText(mantenimientoCUDItem.labname)
        editMantenimientoBinding.ctvServiceTag.setText(mantenimientoCUDItem.computadora)
        if (cb.isNotEmpty()) {
            for (c in cb) {
                if (cb.contains(editMantenimientoBinding.cbExterna.text.toString()))
                    editMantenimientoBinding.cbExterna.isChecked = true
                if (cb.contains(editMantenimientoBinding.cbInterna.text.toString()))
                    editMantenimientoBinding.cbInterna.isChecked = true
                if (cb.contains(editMantenimientoBinding.cbCambio.text.toString()))
                    editMantenimientoBinding.cbCambio.isChecked = true
            }
        }
        editMantenimientoBinding.etDescripcion.setText(mantenimientoCUDItem.desc)
    }

    private fun confirmarCheckBox(): String {
        val cbExterno = editMantenimientoBinding.cbExterna
        val cbInterno = editMantenimientoBinding.cbInterna
        val cbCambio = editMantenimientoBinding.cbCambio
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