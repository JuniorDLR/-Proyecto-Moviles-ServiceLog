package com.example.servivelog.ui.gestionmantenimiento.view

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.servivelog.databinding.FragmentEditMantenimientoBinding
import com.example.servivelog.domain.model.computer.ComputerItem
import com.example.servivelog.domain.model.lab.LabItem
import com.example.servivelog.domain.model.mantenimiento.MantenimientoCUDItem
import com.example.servivelog.domain.model.mantenimiento.MantenimientoItem
import com.example.servivelog.ui.gestionmantenimiento.adapter.AutoTextLabAdapter
import com.example.servivelog.ui.gestionmantenimiento.adapter.AutotextComptAdapter
import com.example.servivelog.ui.gestionmantenimiento.viewmodel.GestionManteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class FragmentEditMantenimiento : Fragment() {

    private lateinit var editMantenimientoBinding: FragmentEditMantenimientoBinding
    private lateinit var mantenimientoCUDItem: MantenimientoCUDItem
    private val args: FragmentEditMantenimientoArgs by navArgs()
    private val gestionManteViewModel: GestionManteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        editMantenimientoBinding = FragmentEditMantenimientoBinding.inflate(layoutInflater)

        CoroutineScope(Dispatchers.Main).launch {
            val lab = gestionManteViewModel.getAllLabs()
            val comp = gestionManteViewModel.getAllComputers()
            obteniendoDatos(comp)
            setLabAdapter(lab)
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val btneditar = editMantenimientoBinding.btnEditar

        btneditar.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val listL = gestionManteViewModel.getAllLabs()
                val listC = gestionManteViewModel.getAllComputers()
                editarDatos(it, listL, listC)
            }
        }

        return editMantenimientoBinding.root
    }

    private fun editarDatos(it: View, listL: List<LabItem>, listC: List<ComputerItem>) {

        val lab = listL.find { it.nombre == editMantenimientoBinding.ctvLab.text.toString() }
        val comp =
            listC.find { it.ubicacion == editMantenimientoBinding.ctvLab.text.toString() && it.serviceTag == editMantenimientoBinding.ctvServiceTag.text.toString() }
        val tipoMant = confirmarCheckBox()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) // formato deseado
        val date = Date() // obtiene la fecha actual
        val formattedDate = dateFormat.format(date)

        if (lab == null || comp == null || tipoMant == "" || editMantenimientoBinding.etDescripcion.text.toString()
                .isEmpty()
        )
            Toast.makeText(
                requireContext(),
                "No se puede editar el registro porque faltan datos, el laboratorio no existe o la computadora no existe",
                Toast.LENGTH_SHORT
            ).show()
        else {
            mantenimientoCUDItem.labname = lab.nombre
            mantenimientoCUDItem.computadora = comp.serviceTag
            mantenimientoCUDItem.tipoLimpieza = tipoMant
            mantenimientoCUDItem.desc = editMantenimientoBinding.etDescripcion.text.toString()
            mantenimientoCUDItem.dia = formattedDate

            gestionManteViewModel.updateMantenimiento(mantenimientoCUDItem)
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }

    }

    private fun obteniendoDatos(comp: List<ComputerItem>) {
        mantenimientoCUDItem = args.mantenimiento

        val cb = mantenimientoCUDItem.tipoLimpieza.split(". ")

        editMantenimientoBinding.ctvLab.setText(mantenimientoCUDItem.labname)
        editMantenimientoBinding.ctvServiceTag.setText(mantenimientoCUDItem.computadora)
        val com = comp.filter { it.ubicacion == mantenimientoCUDItem.labname }
        setCompAdapter(com)
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

    private fun setLabAdapter(lab: List<LabItem>) {
        val adapter = AutoTextLabAdapter(
            requireContext(),
            R.layout.simple_dropdown_item_1line,
            lab.map { it.nombre })
        editMantenimientoBinding.ctvLab.setAdapter(adapter)

        editMantenimientoBinding.ctvLab.setOnItemClickListener { _, _, position, _ ->
            val selectedLab = lab[position]
            CoroutineScope(Dispatchers.Main).launch {
                val computerList = gestionManteViewModel.getAllComputers()
                    .filter { it.ubicacion == selectedLab.nombre }
                setCompAdapter(computerList)
            }
        }
    }

    private fun setCompAdapter(comp: List<ComputerItem>) {
        val adapter = AutotextComptAdapter(
            requireActivity(),
            R.layout.simple_dropdown_item_1line,
            comp.map { it.serviceTag })
        editMantenimientoBinding.ctvServiceTag.setAdapter(adapter)
    }
}