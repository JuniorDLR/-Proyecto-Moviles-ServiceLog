package com.example.servivelog.ui.usuario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.servivelog.databinding.FragmentRegistroUsuarioBinding


class FragmentRegistroUsuario : Fragment() {

    private lateinit var RegistroUBinding: FragmentRegistroUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        RegistroUBinding = FragmentRegistroUsuarioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return RegistroUBinding.root
    }


}