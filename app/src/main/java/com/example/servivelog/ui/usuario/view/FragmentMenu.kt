package com.example.servivelog.ui.usuario.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.servivelog.databinding.FragmentMenuBinding


import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentMenu : Fragment() {

    private lateinit var MenuBinding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MenuBinding = FragmentMenuBinding.inflate(layoutInflater)
    }
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {


            return MenuBinding.root
        }
    }