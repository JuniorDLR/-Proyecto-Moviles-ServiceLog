package com.example.servivelog.ui.gestionlaboratorio.view

import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.servivelog.R
import com.example.servivelog.databinding.FragmentAgregarLaboratorioBinding
import com.example.servivelog.domain.model.lab.InsertLab
import com.example.servivelog.ui.gestionlaboratorio.viewmodel.GestionLabViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAgregarLaboratorio : Fragment() {
    private lateinit var agregarLaboratorioBinding: FragmentAgregarLaboratorioBinding
    private val gestionLabViewModel: GestionLabViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        agregarLaboratorioBinding = FragmentAgregarLaboratorioBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        val btnAgregar = agregarLaboratorioBinding.btnGuardar

        btnAgregar.setOnClickListener{
            gestionLabViewModel.insertLab(enviarDatos())
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }

        return agregarLaboratorioBinding.root
    }

    private fun enviarDatos(): InsertLab {
       return InsertLab(
           nombre = agregarLaboratorioBinding.NombreLabs.text.toString(),
           descripcion = agregarLaboratorioBinding.etDescripcionLabs.text.toString()
       )
    }


}