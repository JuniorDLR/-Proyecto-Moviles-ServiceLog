package com.example.servivelog.ui.gestiondiagnostico.adapter
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.servivelog.R

class ImageAdapter(
    private val images: MutableList<Bitmap>,
    private val imageCountListener: ImageCountListener
) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val itemView = inflater.inflate(R.layout.item_viewpaguer, container, false)
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val deleteButton: ImageView = itemView.findViewById(R.id.delete_button)

        val image = images[position]
        imageView.setImageBitmap(image)

        deleteButton.setOnClickListener {
            onDeleteClick(position)
        }

        container.addView(itemView)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return images.size
    }

    fun addImage(image: Bitmap) {
        images.add(image)
        notifyDataSetChanged()
        imageCountListener.onImageAdded(images.size)
    }

    fun onDeleteClick(position: Int) {
        if (position >= 0 && position < images.size) {
            images.removeAt(position)
            notifyDataSetChanged()
            imageCountListener.onImageRemoved(images.size)

        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    interface ImageCountListener {
        fun onImageAdded(imageCount: Int)
        fun onImageRemoved(imageCount: Int)
    }
    fun hasImageAtPosition(position: Int): Boolean {
        return position >= 0 && position < images.size
    }


}
