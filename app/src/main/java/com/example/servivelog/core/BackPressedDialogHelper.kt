package com.example.servivelog.core

import android.content.Context
import androidx.appcompat.app.AlertDialog

object BackPressedDialogHelper {

    fun showLogoutConfirmationDialog(context: Context, onConfirmLogout: () -> Unit) {
        val alertDialog = AlertDialog.Builder(context)
            .setTitle("Cerrar sesión")
            .setMessage("¿Estás seguro de que deseas cerrar sesión?")
            .setPositiveButton("Sí") { _, _ ->
                onConfirmLogout.invoke()
            }
            .setNegativeButton("No", null)
            .create()

        alertDialog.show()
    }
}