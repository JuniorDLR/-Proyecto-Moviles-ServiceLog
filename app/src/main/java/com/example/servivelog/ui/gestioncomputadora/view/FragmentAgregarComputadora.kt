package com.example.servivelog.ui.gestioncomputadora.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.servivelog.R
import com.example.servivelog.databinding.FragmentAgregarComputadoraBinding
import com.example.servivelog.domain.model.computer.InsertItem
import com.example.servivelog.domain.model.lab.LabItem
import com.example.servivelog.ui.gestioncomputadora.viewmodel.GestionCompViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentAgregarComputadora : Fragment() {

    private lateinit var agregarComputadoraBinding: FragmentAgregarComputadoraBinding
    private val gestionCompViewModel: GestionCompViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        agregarComputadoraBinding = FragmentAgregarComputadoraBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val agregarBtn = agregarComputadoraBinding.btnAgregar
        agregarBtn.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                var labItem = gestionCompViewModel.getAllLabs()
                gestionCompViewModel.insertComputer(enviarDatos(labItem))
                val navController = Navigation.findNavController(it)
                navController.popBackStack()//popbackstack te devuelve al fragment anterior y cierra eso

            }

        }
        return agregarComputadoraBinding.root
    }

    private fun enviarDatos(labs: List<LabItem>): InsertItem {

        var lab: LabItem = LabItem(0, "", "")
        for (l in labs) {

            if (l.nombre == agregarComputadoraBinding.etUbicacion.text.toString()) {
                lab = l
            }

        }

        return InsertItem(
            nombre = agregarComputadoraBinding.etNombre.text.toString(),
            descripcion = agregarComputadoraBinding.etDescripcion.text.toString(),
            marca = agregarComputadoraBinding.etMarca.text.toString(),
            modelo = agregarComputadoraBinding.etModelo.text.toString(),
            procesador = agregarComputadoraBinding.etProcesador.text.toString(),
            ram = agregarComputadoraBinding.etRam.text.toString().toInt(),
            almacenamiento = agregarComputadoraBinding.etAlmacenamiento.text.toString().toInt(),
            serviceTag = agregarComputadoraBinding.etServiceTag.text.toString(),
            noInventario = agregarComputadoraBinding.etInventario.text.toString(),
            ubicacion = lab.nombre
        )
    }
}