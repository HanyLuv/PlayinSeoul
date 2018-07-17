package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTourInformation;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.util.ImageLoderUtils;

import java.util.ArrayList;

import static com.work.hany.playinseoul.model.Section.*;
import static com.work.hany.playinseoul.model.Section.ItemType.NOTHING;

public class TourDetailRecyclerViewAdapter extends RecyclerView.Adapter<TourDetailRecyclerViewAdapter.ViewHolder> {


    //https://stackoverflow.com/questions/26245139/how-to-create-recyclerview-with-multiple-view-type

    private ArrayList<Section> sections;

    public TourDetailRecyclerViewAdapter(ArrayList<Section> sections) {
        this.sections = sections;

    }

    public <T>void updateSection(ItemType type,T data) {
        for (Section section : sections) {
            if (section.getType().equals(type)) {
                section.setData(data);
            }
        }
    }

    //TODO getCurrentItemType 와 onCreateViewHolder 가 null을 리턴하는 방식이 마음에 안든당..생각해보자.

    private ItemType getCurrentItemType(int position) {
       for (ItemType itemType : ItemType.values()) {
           if (itemType.getCode() == position) return itemType;
       }

       return NOTHING;
    }

    @NonNull
    @Override
    public TourDetailRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TourDetailRecyclerViewAdapter.ViewHolder viewHolder = null;
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        ItemType currentItemType = getCurrentItemType(viewType);

        switch (currentItemType){
            case IMAGE:
                View imageRowView= inflater.inflate(R.layout.detail_recycler_row_image_itme,null,false);
                viewHolder = new ImageViewHolder(imageRowView);
                break;

            case PHOTOS:
                View photosRowView= inflater.inflate(R.layout.detail_recycler_row_photos_itme,null,false);
                viewHolder = new PhotosViewHolder(photosRowView);
                break;

            case INFORMATION:
                View informationRowView= inflater.inflate(R.layout.detail_recycler_row_information_itme,null,false);
                viewHolder = new InformationViewHolder(informationRowView);
                break;

            case MAP:
                View mapRowView= inflater.inflate(R.layout.detail_recycler_row_map_itme,null,false);
                viewHolder = new MapViewHolder(mapRowView);
                break;
            case INTRO:
                View introRowView= inflater.inflate(R.layout.detail_recycler_row_intro_itme,null,false);
                viewHolder = new IntroViewHolder(introRowView);
                break;


        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind((sections.get(position).getData()));
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    @Override
    public int getItemViewType(int position) {
        return sections.get(position).getType().getCode();
    }

    class ImageViewHolder extends ViewHolder<String> {
        private ImageView imageView;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detail_tour_image);
        }

        @Override
        public void bind(String url) {
            ImageLoderUtils.lodeURI(imageView, url);
        }

    }

    class PhotosViewHolder extends ViewHolder<ArrayList<TourPhoto>>{
        public PhotosViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(ArrayList<TourPhoto> tourPhotos) {

        }
    }

    class MapViewHolder extends ViewHolder{
        public MapViewHolder(View itemView) {
            super(itemView);
        }
        @Override
        public void bind(Object data) {

        }
    }

    class IntroViewHolder extends ViewHolder {
        public IntroViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(Object data) {

        }
    }

    class InformationViewHolder extends ViewHolder<AreaTourInformation>{
        public InformationViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(AreaTourInformation data) {

        }
    }

    abstract class ViewHolder<T> extends  RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
        abstract public void bind(T data);
    }
}
