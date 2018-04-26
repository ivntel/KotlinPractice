package com.example.ivan.kotlinpractice

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.ivan.kotlinpractice.objects.Model

class TItleAdapter(private val mContext: Context, private val searchItems: List<Model.Search>) : RecyclerView.Adapter<TItleAdapter.MyViewHolder>() {

        class MyViewHolder(view: View  ) : RecyclerView.ViewHolder(view) , View.OnClickListener {
            override fun onClick(p0: View?) {
            }

            var title: TextView = view.findViewById<TextView>(R.id.title)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.row_title, parent, false)

            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val searchItem = searchItems[position]
            holder.title.text = searchItem.title
            }

        override fun getItemCount(): Int {
            return searchItems.size
        }
}