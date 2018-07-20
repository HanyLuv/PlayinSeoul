package com.work.hany.playinseoul.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.main.MainFragment;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.util.ConverterUtils;
import com.work.hany.playinseoul.util.ImageLoderUtils;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private ArrayList<AreaTour> areaTourList;
    private MainFragment.MainItemListener mainItemListener;

    public void setTourList(ArrayList<AreaTour> areaTourList){
        this.areaTourList = checkNotNull(areaTourList);
        notifyDataSetChanged();
    }

    public MainRecyclerViewAdapter(ArrayList<AreaTour> areaTourList, MainFragment.MainItemListener mainItemListener) {
        this.mainItemListener = mainItemListener;
        this.areaTourList = areaTourList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_row_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(areaTourList.get(position));
    }

    @Override
    public int getItemCount() {
        return areaTourList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tourImageView;
        private TextView tourTextView;
        private TextView tourContentTypeTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            tourImageView = itemView.findViewById(R.id.tourimage);
            tourTextView = itemView.findViewById(R.id.tourtitle);
            tourContentTypeTextView = itemView.findViewById(R.id.tourcontenttype);

        }

        void bindItem(final AreaTour areaTour) {
            ImageLoderUtils.lodeURI(tourImageView, areaTour.getLargeImage());
            tourTextView.setText(areaTour.getContentTitle());
            tourContentTypeTextView.setText(ConverterUtils.convertContentType(areaTour.getContentTypeId()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainItemListener.onTourClick(areaTour);

                }
            });

        }
    }
}
