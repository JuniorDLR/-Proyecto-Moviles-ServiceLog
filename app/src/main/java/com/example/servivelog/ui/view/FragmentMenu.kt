package com.example.servivelog.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.servivelog.databinding.FragmentMenuBinding

class FragmentMenu : Fragment() {

    private lateinit var MenuBinding: FragmentMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        MenuBinding = FragmentMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return MenuBinding.root
    }


}