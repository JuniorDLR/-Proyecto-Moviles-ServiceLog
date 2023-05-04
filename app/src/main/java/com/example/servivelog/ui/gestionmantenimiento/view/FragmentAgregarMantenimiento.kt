package com.example.servivelog.ui.gestionmantenimiento.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.servivelog.databinding.FragmentAgregarMantenimientoBinding
import com.example.servivelog.domain.model.MantenimientoItem
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

        agregarbtn.setOnClickListener{
            gestionManteViewModel.insertMantenimiento(enviarDatos())
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }
        return agregarMantenimientoBinding.root
    }

    private fun enviarDatos(): MantenimientoItem {
        val datosL = gestionManteViewModel.buscarlab(agregarMantenimientoBinding.ctvLab.text.toString())
        val datosC = gestionManteViewModel.buscarComp(agregarMantenimientoBinding.ctvServiceTag.text.toString())
        val tipoMant = confirmarCheckBox()
        return MantenimientoItem(
            datosL.idL,
            datosL.nombre,
            datosC.idC,
            datosC.serviceTag,
            tipoMant,
            agregarMantenimientoBinding.etDescripcion.text.toString()
        )
    }

    private fun confirmarCheckBox(): String {
        val cbExterno = agregarMantenimientoBinding.cbExterna
        val cbInterno = agregarMantenimientoBinding.cbInterna
        val cbCambio = agregarMantenimientoBinding.cbCambio
        if (cbExterno.isChecked && cbInterno.isChecked && cbCambio.isChecked)
            return cbExterno.text.toString() + " " + cbInterno.text.toString() + cbCambio.text.toString()
        else if (cbExterno.isChecked && cbInterno.isChecked && !cbCambio.isChecked)
            return cbExterno.text.toString() + " " +cbInterno.text.toString()
        else if (cbExterno.isChecked && !cbInterno.isChecked && cbCambio.isChecked)
            return cbExterno.text.toString() + " " + cbCambio.text.toString()
        else if (!cbExterno.isChecked && cbInterno.isChecked && cbCambio.isChecked)
            return cbInterno.text.toString() + " " + cbCambio.text.toString()
        else if (cbExterno.isChecked && !cbInterno.isChecked && !cbCambio.isChecked)
            return cbExterno.text.toString()
        else if (!cbExterno.isChecked && cbInterno.isChecked && !cbCambio.isChecked)
            return cbInterno.toString()
        else if (!cbExterno.isChecked && !cbInterno.isChecked && cbCambio.isChecked)
            return cbCambio.text.toString()
        else
            return ""
    }
}