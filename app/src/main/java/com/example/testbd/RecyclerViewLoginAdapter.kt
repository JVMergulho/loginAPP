package com.example.testbd

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewLoginAdapter: RecyclerView.Adapter<RecyclerViewLoginAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textView: TextView

        init{
            textView = view.findViewById(R.id.text_view_row)
        }
    }

    val data: MutableList<RoomEntity_login> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewLoginAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewLoginAdapter.ViewHolder, position: Int) {
        holder.textView.text = data[position].username
    }

    fun addAll(list: List<RoomEntity_login>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

}