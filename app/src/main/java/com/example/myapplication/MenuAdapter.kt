package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(
    private val daftarMenu: List<MenuItem>,
    private val onJumlahChanged: () -> Unit
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val menuImage: ImageView = itemView.findViewById(R.id.MenuImage)
        val tvNamaMenu: TextView = itemView.findViewById(R.id.tvNamaMenu)
        val tvHargaMenu: TextView = itemView.findViewById(R.id.tvHargaMenu)
        val tvJumlah: TextView = itemView.findViewById(R.id.tvJumlah)
        val btnTambah: ImageView = itemView.findViewById(R.id.btnTambah)
        val btnKurang: ImageView = itemView.findViewById(R.id.btnKurang)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = daftarMenu[position]

        holder.menuImage.setImageResource(item.imageResId)
        holder.tvNamaMenu.text = item.nama
        holder.tvHargaMenu.text = "Rp${item.harga}"
        holder.tvJumlah.text = item.jumlah.toString()

        holder.btnTambah.setOnClickListener {
            item.jumlah++
            holder.tvJumlah.text = item.jumlah.toString()
            onJumlahChanged()
        }

        holder.btnKurang.setOnClickListener {
            if (item.jumlah > 0) {
                item.jumlah--
                holder.tvJumlah.text = item.jumlah.toString()
                onJumlahChanged()
            }
        }
    }

    override fun getItemCount(): Int = daftarMenu.size
}