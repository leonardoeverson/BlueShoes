package com.example.blueshoes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blueshoes.R
import com.example.blueshoes.domain.NavMenuItem

class NavManuItemsAdapter (val items: List<NavMenuItem>): RecyclerView.Adapter<NavManuItemsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.nav_menu_item, parent, false)

        return ViewHolder(layout)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }

    inner class ViewHolder( itemView: View): RecyclerView.ViewHolder( itemView ){
        private val ivIcon : ImageView
        private val tvLabel: TextView

        init {
            ivIcon = itemView.findViewById(R.id.iv_icon)
            tvLabel = itemView.findViewById(R.id.tv_label)
        }
    }

}