package com.example.fragement_container.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.fragement_container.Models.balancehistory
import com.example.fragement_container.R
import com.google.android.material.imageview.ShapeableImageView


class MybalanceAdapter(public val userhistorylist:ArrayList<balancehistory>):
    RecyclerView.Adapter<MybalanceAdapter.mytemplateviewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mytemplateviewholder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.first_fragement_list_item,parent,false)

        return mytemplateviewholder(view);
    }

    override fun getItemCount(): Int {
       return userhistorylist.size
    }

    override fun onBindViewHolder(holder: mytemplateviewholder, position: Int) {
        val currentitem=userhistorylist[position]
        holder.carrybage.setImageResource(currentitem.caarmoneyimage)
        holder.username.setText(currentitem.username)
        holder.datehistory.setText(currentitem.Datehistory)
        holder.price.setText(currentitem.balance)
    }

    class mytemplateviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carrybage: ImageView = itemView.findViewById(R.id.carrybage)
        val username: TextView = itemView.findViewById(R.id.username)
        val datehistory: TextView = itemView.findViewById(R.id.datehistory)
        val price: TextView = itemView.findViewById(R.id.price)

    }
}