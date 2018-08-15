package com.work.hany.playinseoul.tourlist.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.work.hany.playinseoul.R
import com.work.hany.playinseoul.network.AreaTour
import com.work.hany.playinseoul.util.ImageLoderUtils
import com.work.hany.playinseoul.util.toDateString
import kotlinx.android.synthetic.main.main_recycler_row_item.view.*


class TourListRecyclerViewAdapter(private var tourArrayList: ArrayList<AreaTour>, var itemListener: ItemListener): RecyclerView.Adapter<TourListRecyclerViewAdapter.TourViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
       return TourViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_recycler_row_item,parent,false))
    }

    interface ItemListener {
        fun onTourClicked(tour: AreaTour)
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

    inner class TourViewHolder(private var itemVew: View) : ViewHolder(itemVew) {
        fun bind(tour: AreaTour) {

            ImageLoderUtils.lodeURI(itemVew.tourContentImageView, tour.largeImage, RequestOptions().fitCenter())
            var readCountString = StringBuilder().append("조회수 ").append(tour.readCount).toString()

            itemVew.tourContentShowTextView.text = readCountString
            itemVew.tourContentTitleTextView.text = tour.contentTitle
            itemVew.tourContentAddrTextView.text = tour.fullAddress
            itemVew.tourContentDateTextView.text = tour.modifiedTime.toDateString()

            itemVew.setOnClickListener {
                itemListener.onTourClicked(tour)
            }
        }

    }
}
