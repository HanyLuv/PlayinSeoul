package com.work.hany.playinseoul.toursearch.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.network.Area
import com.work.hany.playinseoul.toursearch.vo.SearchItem
import com.work.hany.playinseoul.toursearch.adapter.SearchAttrRecyclerViewAdapter.ItemListener

/** 디비에 저장해놔야한다. 지역만 호출하기엔 데이터 아까워 ㅠ*/
class SearchRecyclerViewAdapter(private var areaItems: ArrayList<SearchItem>, private var itemListener: ItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_SEARCH = 0
    }

    private lateinit var recyclerViewPool: RecyclerView.RecycledViewPool

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.search_recycler_row_item, parent, false)
        var viewHolder = SearchViewHolder(view)
        viewHolder.itemRecyclerView.recycledViewPool = recyclerViewPool

        return viewHolder
    }

    override fun getItemCount(): Int = areaItems.size

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerViewPool = recyclerView.recycledViewPool
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_SEARCH
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentPosition = if (holder.adapterPosition != RecyclerView.NO_POSITION) { holder.adapterPosition } else { position }

        if (holder.itemViewType != TYPE_SEARCH) {
            val holderFromRecyclerPool = recyclerViewPool.getRecycledView(getItemViewType(position))
            (holderFromRecyclerPool as SearchViewHolder).bind(areaItems[currentPosition])

        } else {
            (holder as SearchViewHolder).bind(areaItems[currentPosition])

        }

    }

    fun updateSearchItem(type: SearchItem.SearchItemType, items: ArrayList<Area>) {
        val it = areaItems.listIterator()
        while (it.hasNext()) {
            val searchItem = it.next()
            if (searchItem.itemType == type) {
                searchItem.codes = items
                notifyItemChanged(it.previousIndex())
            }
        }

    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemRecyclerView: RecyclerView = itemView.findViewById(R.id.searchAttrRecyclerView)
        private var itemTitleTextView: TextView = itemView.findViewById(R.id.searchTitleTextView)

        fun bind(item: SearchItem) {
            itemRecyclerView.setHasFixedSize(true)
            itemRecyclerView.layoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.HORIZONTAL)
            itemRecyclerView.adapter = SearchAttrRecyclerViewAdapter(item, itemListener)
            itemTitleTextView.text = item.itemType.name

        }
    }

}