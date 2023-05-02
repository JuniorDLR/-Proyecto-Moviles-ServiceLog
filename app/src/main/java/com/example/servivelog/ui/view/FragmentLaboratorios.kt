package com.example.servivelog.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    ): View? {

        return laboratorioBinding.root
    }


}