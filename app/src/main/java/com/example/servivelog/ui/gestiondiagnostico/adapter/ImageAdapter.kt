package com.example.servivelog.ui.gestiondiagnostico.adapter


import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.servivelog.R

class ImageAdapter(private val bitmapList: MutableList<Bitmap>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View, private val adapter: ImageAdapter) :
        RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.image_view)
        private val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)

        fun bind(bitmap: Bitmap) {
            imageView.setImageBitmap(bitmap)

            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    adapter.removeImage(position)
                }
            }
        }
    }

    fun addImage(bitmap: Bitmap) {
        bitmapList.add(bitmap)
        notifyDataSetChanged()
    }

    fun clear() {
        bitmapList.clear()
        notifyDataSetChanged()
    }

    fun removeImage(position: Int) {
        bitmapList.removeAt(position)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_viewpaguer, parent, false)
        return ViewHolder(inflater, this) // Pasar la referencia del adaptador
    }

    override fun getItemCount(): Int {
        return bitmapList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bitmap = bitmapList[position]
        holder.bind(bitmap)
    }
}
