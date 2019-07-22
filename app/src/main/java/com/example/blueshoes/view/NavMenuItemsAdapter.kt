package com.example.blueshoes.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blueshoes.R
import com.example.blueshoes.domain.NavMenuItem

class NavMenuItemsAdapter (val items: List<NavMenuItem>): RecyclerView.Adapter<NavMenuItemsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.nav_menu_item, parent, false)

        return ViewHolder(layout)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder( itemView: View): RecyclerView.ViewHolder( itemView ){
        private val ivIcon : ImageView
        private val tvLabel: TextView

        init {
            ivIcon = itemView.findViewById(R.id.iv_icon)
            tvLabel = itemView.findViewById(R.id.tv_label)
        }

        fun setData(item: NavMenuItem){
            tvLabel.text = item.label

            if(item.iconId != NavMenuItem.DEFAULT_ICON_ID){
                ivIcon.setImageResource(item.iconId)
                ivIcon.visibility = View.VISIBLE
            }else{
                ivIcon.visibility = View.GONE
            }
        }
    }

}