package com.example.processoseletivoletrasmusbr.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.processoseletivoletrasmusbr.R
import com.example.processoseletivoletrasmusbr.model.Title


// ItemAdapter takes a Title instance from the list returned by loadTitles(),
// and turns it into a list item view, so that it can be displayed in the RecyclerView.
class ItemAdapter(
    private val dataset: List<Title>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    // RecyclerView doesn't interact directly with item views,vbut deals with ViewHolders instead.
    // A ViewHolder represents a single list item view in RecyclerView, and can be reused when possible.

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val scoreTextView: TextView = view.findViewById(R.id.score)
        val titleTextView: TextView = view.findViewById(R.id.title)
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // parent: view group that the new list item view will be attached to as a child
        // viewType: only important when we have more than one list item view

        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.scoreTextView.text = "0"
        holder.titleTextView.text = item.name
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    override fun getItemCount() = dataset.size

}