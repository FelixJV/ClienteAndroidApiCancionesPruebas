package com.example.moviles1felixjv.ui.pantallaFotos

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.moviles1felixjv.R
import com.example.moviles1felixjv.domain.domain.modelo.Foto


class FotosAdapter(
    val actions: FotoActions,
) : ListAdapter<Foto, FotosItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FotosItemViewholder {
        return FotosItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.foto_view, parent, false),
            actions,
        )
    }
    override fun onBindViewHolder(holder: FotosItemViewholder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    class DiffCallback : DiffUtil.ItemCallback<Foto>() {
        override fun areItemsTheSame(oldItem: Foto, newItem: Foto): Boolean {
            return oldItem.idFoto == newItem.idFoto
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Foto, newItem: Foto): Boolean {
            return oldItem == newItem
        }
    }
    interface FotoActions {
        fun onItemClick(foto: Foto)
    }
}
