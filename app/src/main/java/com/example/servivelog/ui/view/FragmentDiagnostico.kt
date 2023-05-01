package com.example.servivelog.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.servivelog.databinding.FragmentDiagnosticoBinding


class FragmentDiagnostico : Fragment() {
    private lateinit var FragmentDiagnostico: FragmentDiagnosticoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentDiagnostico = FragmentDiagnosticoBinding.inflate(layoutInflater)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return FragmentDiagnostico.root
    }


}