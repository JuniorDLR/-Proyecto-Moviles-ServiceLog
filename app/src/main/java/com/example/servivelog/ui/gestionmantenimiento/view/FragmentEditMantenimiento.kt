package com.example.servivelog.ui.gestionmantenimiento.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.servivelog.databinding.FragmentEditMantenimientoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentEditMantenimiento : Fragment() {

    private lateinit var editMantenimientoBinding: FragmentEditMantenimientoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        editMantenimientoBinding = FragmentEditMantenimientoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return editMantenimientoBinding.root
    }
}