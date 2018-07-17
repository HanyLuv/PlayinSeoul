package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TourDetailRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

/**
 *
 [이미지]
 [소개]
 [운영정보]
 [사진들]
 [지도]
 [그외 추가]
 *
 */
    public static final int ITEM_TYPE_IMAGE = 0;
    public static final int ITEM_TYPE_INTORO = 1;
    public static final int ITEM_TYPE_INFORMATION = 2;
    public static final int ITEM_TYPE_PHOTOS = 3;
    public static final int ITEM_TYPE_MAP = 4;


//https://stackoverflow.com/questions/26245139/how-to-create-recyclerview-with-multiple-view-type
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ImageViewHolder extends RecyclerView.ViewHolder{
        public ImageViewHolder(View itemView) {
            super(itemView);
        }
    }

    class IntoroViewHolder extends RecyclerView.ViewHolder{
        public IntoroViewHolder(View itemView) {
            super(itemView);
        }
    }

    class InformationViewHolder extends RecyclerView.ViewHolder{
        public InformationViewHolder(View itemView) {
            super(itemView);
        }
    }


    class PhotosViewHolder extends RecyclerView.ViewHolder{
        public PhotosViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MapViewHolder extends RecyclerView.ViewHolder{
        public MapViewHolder(View itemView) {
            super(itemView);
        }
    }
}
