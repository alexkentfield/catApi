package com.example.catapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cat_item.view.*
import com.squareup.picasso.Picasso

class CatAdapter(private val items : ArrayList<CatDO>,
                 private val viewModel : CatViewModel,
                 private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.cat_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvLikeCat.setOnClickListener {
            (it as TextView).setTextColor(ContextCompat.getColor(context, android.R.color.holo_blue_light))
            viewModel.markCatAsFavorite(items[position].id!!)
        }
        Picasso.get().load(items[position].url).centerCrop().resize(700, 600).into(holder.ivCatImage)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val ivCatImage: ImageView = view.cat_image
    val tvLikeCat: TextView = view.like_cat
}