package com.example.servivelog.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.servivelog.R
import com.example.servivelog.databinding.FragmentAgregarLaboratorioBinding

class FragmentAgregarLaboratorio : Fragment() {
    private lateinit var agregarLaboratorioBinding: FragmentAgregarLaboratorioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        agregarLaboratorioBinding = FragmentAgregarLaboratorioBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return agregarLaboratorioBinding.root
    }


}