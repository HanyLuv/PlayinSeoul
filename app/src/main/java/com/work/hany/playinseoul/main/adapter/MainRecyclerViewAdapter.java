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
import com.work.hany.playinseoul.network.AreaTourInformation;
import com.work.hany.playinseoul.util.ImageLoderUtils;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    private ArrayList<AreaTourInformation> areaTourInformationList;
    private MainFragment.MainItemListener mainItemListener;

    public void setTourList(ArrayList<AreaTourInformation> areaTourInformationList){
        this.areaTourInformationList = checkNotNull(areaTourInformationList);
        notifyDataSetChanged();
    }

    public MainRecyclerViewAdapter(ArrayList<AreaTourInformation> areaTourInformationList, MainFragment.MainItemListener mainItemListener) {
        this.mainItemListener = mainItemListener;
        this.areaTourInformationList = areaTourInformationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_row_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindItem(areaTourInformationList.get(position));
    }

    @Override
    public int getItemCount() {
        return areaTourInformationList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tourImageView;
        private TextView tourTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            tourImageView = itemView.findViewById(R.id.tourimage);
            tourTextView = itemView.findViewById(R.id.tourtitle);

        }

        void bindItem(final AreaTourInformation tourInformation) {
            ImageLoderUtils.lodeURI(tourImageView, tourInformation.getLargeImage());
            tourTextView.setText(tourInformation.getContentTitle());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainItemListener.onTourClick(tourInformation);

                }
            });

        }
    }
}
