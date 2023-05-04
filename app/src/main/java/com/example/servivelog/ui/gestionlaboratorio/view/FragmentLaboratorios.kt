package com.example.servivelog.ui.gestionlaboratorio.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.servivelog.R
import com.example.servivelog.databinding.FragmentLaboratoriosBinding


class FragmentLaboratorios : Fragment() {
    private lateinit var laboratorioBinding: FragmentLaboratoriosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        laboratorioBinding = FragmentLaboratoriosBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val btnAdd = laboratorioBinding.fbtnAdd

        btnAdd.setOnClickListener{
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_Fragmentlaboratorios_to_fragmentAgregarLaboratorio)
        }

        return laboratorioBinding.root
    }


}