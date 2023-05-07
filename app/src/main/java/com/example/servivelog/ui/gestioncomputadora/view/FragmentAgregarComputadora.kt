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
import com.example.servivelog.ui.gestioncomputadora.viewmodel.GestionCompViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAgregarComputadora : Fragment() {

    private lateinit var agregarComputadoraBinding: FragmentAgregarComputadoraBinding
    private val gestionCompViewModel : GestionCompViewModel by viewModels()

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
            gestionCompViewModel.insertComputer(enviarDatos())
            val navController = Navigation.findNavController(it)
            navController.popBackStack()//popbackstack te devuelve al fragment anterior y cierra eso
        }
        return agregarComputadoraBinding.root
    }

    private fun enviarDatos(): InsertItem {
        val datosL = gestionCompViewModel.searchLab(agregarComputadoraBinding.etUbicacion.text.toString())
        if (datosL == null){
            Toast.makeText(requireContext(),"No existe laboratorio",Toast.LENGTH_SHORT).show()
        }
        return InsertItem(nombre = agregarComputadoraBinding.etNombre.text.toString(),
            descripcion = agregarComputadoraBinding.etDescripcion.text.toString(),
            marca = agregarComputadoraBinding.etMarca.text.toString(),
            modelo = agregarComputadoraBinding.etModelo.text.toString(),
            procesador = agregarComputadoraBinding.etProcesador.text.toString(),
            ram = agregarComputadoraBinding.etRam.text.toString().toInt(),
            almacenamiento = agregarComputadoraBinding.etAlmacenamiento.text.toString().toInt(),
            serviceTag = agregarComputadoraBinding.etServiceTag.text.toString(),
            noInventario = agregarComputadoraBinding.etInventario.text.toString(),
            ubicacion = agregarComputadoraBinding.etUbicacion.text.toString()
        )
    }
}