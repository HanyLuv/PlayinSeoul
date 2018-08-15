package com.work.hany.playinseoul.tourlist.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.network.AreaTour
import com.work.hany.playinseoul.util.ImageLoderUtils
import kotlinx.android.synthetic.main.main_recycler_row_item.view.*


class TourListRecyclerViewAdapter(var tourArrayList: ArrayList<AreaTour>): RecyclerView.Adapter<TourListRecyclerViewAdapter.TourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.main_recycler_row_item,null,false)
       return TourViewHolder(view)
    }

    override fun getItemCount(): Int = tourArrayList.size

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        var holderPosition = position


        if(holder.adapterPosition != RecyclerView.NO_POSITION) {
            holderPosition = holder.adapterPosition
        }

        var tour = tourArrayList[holderPosition]
        holder.bind(tour)
    }


    class TourViewHolder(var itemVew: View): ViewHolder(itemVew) {
        fun bind(tour: AreaTour){
            ImageLoderUtils.lodeURI(itemVew.tourContentImageView, tour.largeImage)
        }

    }
}
