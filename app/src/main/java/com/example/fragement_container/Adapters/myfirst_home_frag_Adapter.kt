package com.example.fragement_container.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragement_container.Models.first_farg_model
import com.example.fragement_container.R
import com.example.fragement_container.thirdfragement


class myfirst_home_frag_Adapter (public val pricearrlist:ArrayList<first_farg_model>,private val listener: OnItemClickListener ):RecyclerView.Adapter<myfirst_home_frag_Adapter.Mybalcnceviewholdefrgone>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Mybalcnceviewholdefrgone {
      val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.carditemf1,parent,false)
        return Mybalcnceviewholdefrgone(view)
    }


    override fun getItemCount(): Int {
       return pricearrlist.size
    }

    override fun onBindViewHolder(holder: Mybalcnceviewholdefrgone, position: Int) {
        val currentitem=pricearrlist[position]
        holder.balance.setText(currentitem.Balancetxt)
        holder.midbalance.setText(currentitem.mainprice)
        holder.smallbalance.setText(currentitem.Balancetxt)

//        holder.balancecardclick.setOnClickListener {
//            Log.d("TAG", "onBindViewHolder: heloofjf")
//            val mythirdfragement= thirdfragement()
//            mythirdfragement.show(holder.itemView.context, mythirdfragement.tag)
//        }


    }

    inner class Mybalcnceviewholdefrgone(itemView: View):RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val balance:TextView=itemView.findViewById(R.id.balance);
        val midbalance:TextView=itemView.findViewById(R.id.midbalance);
        val smallbalance:TextView=itemView.findViewById(R.id.smallbalance)
        val balancecardclick:CardView=itemView.findViewById(R.id.balancecardclick)
        init {
              itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position=adapterPosition
            if(position!=RecyclerView.NO_POSITION)
            listener.onItemClick(position)
        }
    }
    interface OnItemClickListener{
        fun  onItemClick(position: Int)

    }


}