package com.work.hany.playinseoul.toursearch.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.toursearch.vo.SearchSection

class SearchAttrRecyclerViewAdapter(codeItem: SearchSection, private var itemListener: ItemListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val attrItems = codeItem.codes
    private val attrType = codeItem.itemType

    companion object {
        private const val TYPE_ATTR = 1
    }

    interface ItemListener {
        fun onItemClicked(item: Area, type: SearchSection.SearchItemType)
    }
    private lateinit var searchAttrRecyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        searchAttrRecyclerView = recyclerView
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttrViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_recycler_row_area_item, parent, false)
        return AttrViewHolder(view)
    }

    override fun getItemCount(): Int = attrItems.size

    override fun getItemViewType(position: Int): Int = TYPE_ATTR

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var currentPosition = if (holder.adapterPosition != RecyclerView.NO_POSITION) { holder.adapterPosition } else { position }
        if (holder.itemViewType != TYPE_ATTR) {
            val holderFromRecyclerPool = searchAttrRecyclerView.recycledViewPool.getRecycledView(getItemViewType(position))
            (holderFromRecyclerPool as AttrViewHolder).bind(attrItems[currentPosition])

        } else {
            (holder as AttrViewHolder).bind(attrItems[currentPosition])

        }
    }


    inner class AttrViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var rowTitleTextView = itemView.findViewById<TextView>(R.id.tagTitleTextView)
        fun bind(item: Area) {
            rowTitleTextView.text = item.name
            itemView.setOnClickListener {
                itemListener.onItemClicked(item, attrType)
            }
        }
    }


}