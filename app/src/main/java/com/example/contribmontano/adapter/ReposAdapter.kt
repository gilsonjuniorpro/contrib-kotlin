package com.example.contribmontano.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contribmontano.R
import com.example.contribmontano.service.Repository
import kotlinx.android.synthetic.main.item_repository.view.*

class ReposAdapter(
    private val items: List<Repository>
): RecyclerView.Adapter<ReposAdapter.ReposHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ReposHolder(layout)
    }

    override fun onBindViewHolder(holder: ReposHolder, position: Int) {
        val repo = items[position]
        holder.name.text = repo.name
        holder.url.text = repo.html_url
    }

    class ReposHolder(rootView: View): RecyclerView.ViewHolder(rootView){
        val name: TextView = rootView.tvName
        val url: TextView = rootView.tvUrl
    }

    override fun getItemCount(): Int = items.size
}