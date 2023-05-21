package com.example.servivelog.ui.gestiondiagnostico.view


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.example.servivelog.databinding.FragmentAgregarDiagnosticoBinding
import com.example.servivelog.domain.model.diagnosis.InsertDiagnosis
import com.example.servivelog.ui.gestiondiagnostico.adapter.ImageAdapter
import com.example.servivelog.ui.gestiondiagnostico.viewmodel.GestioDiagnosisViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class FragmentAgregarDiagnostico : Fragment() {
    private var MAX = 4
    private var imagesTaken = 0
    private val bitmapList = mutableListOf<Bitmap>()

    private lateinit var imageAdapter: ImageAdapter
    private lateinit var imageCountTextView: TextView
    private lateinit var viewPager: ViewPager2

    private val gestionDiagnosisViewModel: GestioDiagnosisViewModel by viewModels()
    private lateinit var agregarDiagnosticoBinding: FragmentAgregarDiagnosticoBinding


    // Variables para las rutas de las imágenes
    private var ruta1: String? = null
    private var ruta2: String? = null
    private var ruta3: String? = null
    private var ruta4: String? = null

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intentData: Intent? = result.data
            if (intentData != null) {
                val clipData = intentData.clipData
                if (clipData != null) {
                    // Multiple images selected
                    for (i in 0 until clipData.itemCount) {
                        val imageUri: Uri = clipData.getItemAt(i).uri
                        handleImageUri(imageUri)
                    }
                } else {
                    // Single image selected
                    val imageUri: Uri? = intentData.data
                    if (imageUri != null) {
                        handleImageUri(imageUri)
                    }
                }

                // Update the UI with the selected images
                imageAdapter.addImage(bitmapList.last())
                actualizarConteo()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        agregarDiagnosticoBinding = FragmentAgregarDiagnosticoBinding.inflate(inflater, container, false)

        val btnAgregar = agregarDiagnosticoBinding.btnGuardar
        val btnFoto = agregarDiagnosticoBinding.btnFoto
        val btnGuardar = agregarDiagnosticoBinding.btnGuardar

        viewPager = agregarDiagnosticoBinding.viewPager
        imageAdapter = ImageAdapter(bitmapList)
        viewPager.adapter = imageAdapter
        imageCountTextView = agregarDiagnosticoBinding.conteo

        btnGuardar.setOnClickListener {
            agregarDatos()
        }

        btnAgregar.setOnClickListener {
            val navController = Navigation.findNavController(it)
            navController.popBackStack()
        }

        btnFoto.setOnClickListener {
            openGallery()
        }

        return agregarDiagnosticoBinding.root
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        galleryLauncher.launch(galleryIntent)
    }

    private fun handleImageUri(imageUri: Uri) {
        if (imagesTaken < MAX) {
            val bitmap = getBitmapFromUri(imageUri)
            if (bitmap != null) {
                // Verificar si la imagen ya existe en la lista
                val imageExists = bitmapList.any { it.sameAs(bitmap) }
                if (!imageExists) {
                    bitmapList.add(bitmap)
                    imagesTaken++

                    // Guardar la ruta de la imagen en la variable correspondiente
                    when (imagesTaken) {
                        1 -> ruta1 = imageUri.toString()
                        2 -> ruta2 = imageUri.toString()
                        3 -> ruta3 = imageUri.toString()
                        4 -> ruta4 = imageUri.toString()
                    }
                } else {
                    Toast.makeText(requireContext(), "La imagen ya ha sido seleccionada", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "No se pudo cargar la imagen", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Ya has seleccionado el número máximo de imágenes", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun agregarDatos() {
        lifecycleScope.launch {
            val laboratorio = agregarDiagnosticoBinding.etLabs.text.toString()
            val servicio = agregarDiagnosticoBinding.etServiPc.text.toString()
            val descripcion = agregarDiagnosticoBinding.etDescripcionDiagnostico.text.toString()

            if (laboratorio.isEmpty() || servicio.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(requireContext(), "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val laboratoriosDeferred = gestionDiagnosisViewModel.viewModelScope.async {
                    gestionDiagnosisViewModel.getAllLaboratories()
                }
                val laboratorios = laboratoriosDeferred.await()

                // Utiliza la lista de laboratorios para realizar las operaciones deseadas
                val laboratorioEncontrado = laboratorios.find { it.nombre == laboratorio }
                if (laboratorioEncontrado != null) {
                    // El laboratorio existe, puedes realizar las operaciones necesarias
                    // ...

                    val insertDiagnosis = InsertDiagnosis(
                        nombrelab = laboratorio,
                        ServiceTag = servicio,
                        descripcion = descripcion,
                        ruta1 = ruta1 ?: "",
                        ruta2 = ruta2 ?: "",
                        ruta3 = ruta3 ?: "",
                        ruta4 = ruta4 ?: ""
                    )

                    gestionDiagnosisViewModel.insertDiagnosi(insertDiagnosis)
                    Toast.makeText(requireContext(), "Datos guardados correctamente", Toast.LENGTH_SHORT).show()
                } else {
                    // El laboratorio no existe, muestra un mensaje de error
                    Toast.makeText(requireContext(), "Laboratorio no válido", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun actualizarConteo() {
        val count = bitmapList.size
        imageCountTextView.text = "$count/$MAX"
    }
}

