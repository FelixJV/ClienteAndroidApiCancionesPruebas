package com.example.moviles1felixjv.ui.pantallaFotos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.moviles1felixjv.databinding.AlbumViewBinding
import com.example.moviles1felixjv.databinding.FotoViewBinding
import com.example.moviles1felixjv.domain.domain.modelo.Foto

class FotosItemViewholder(itemView: View, val actions:FotosAdapter.FotoActions) : RecyclerView.ViewHolder(itemView) {

    private val binding = FotoViewBinding.bind(itemView)

    fun bind(item: Foto){
        with(binding) {
            textViewNombre.text = item.titulo
            imageView.load(item.foto){
                crossfade(true)
            }
            itemView.setBackgroundResource(android.R.color.white)



            itemView.setOnLongClickListener{
                true
            }
            itemView.setOnClickListener {

                actions.onItemClick(item)
            }
        }
    }
}