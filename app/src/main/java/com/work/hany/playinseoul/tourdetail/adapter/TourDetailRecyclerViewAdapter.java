package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelInformation;
import com.work.hany.playinseoul.util.ImageLoderUtils;

import java.util.ArrayList;

import static com.work.hany.playinseoul.model.Section.*;
import static com.work.hany.playinseoul.model.Section.ItemType.NOTHING;
import static dagger.internal.Preconditions.checkNotNull;

public class TourDetailRecyclerViewAdapter extends RecyclerView.Adapter<TourDetailRecyclerViewAdapter.ViewHolder> {


    //https://stackoverflow.com/questions/26245139/how-to-create-recyclerview-with-multiple-view-type

    private ArrayList<Section> sections;

    public TourDetailRecyclerViewAdapter(ArrayList<Section> sections) {
        this.sections = sections;

    }

    public <T>void addSection(ItemType type, T data){
        sections.add(new Section(type,data));
        notifyDataSetChanged();
    }

    public <T>void updateSection(ItemType type,T data) {
        for(int position = 0, end = sections.size(); position < end; position++ ){
            if (sections.get(position).getType().equals(type)) {
                sections.get(position).setData(data);
                notifyItemChanged(position);
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
            case COURSE:
                View courseView= inflater.inflate(R.layout.detail_recycler_row_course_itme,null,false);
                viewHolder = new CourseViewHolder(courseView);
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

    class IntroViewHolder extends ViewHolder<AreaTour> {
        private TextView contentIntroTextView;
        private TextView contentTitleTextView;
        private TextView contentAddrTextView;

        public IntroViewHolder(View itemView) {
            super(itemView);
            contentIntroTextView = itemView.findViewById(R.id.tour_information_text_view);
            contentTitleTextView = itemView.findViewById(R.id.tour_title_text_view);
            contentAddrTextView = itemView.findViewById(R.id.tour_addr_text_view);
        }

        @Override
        public void bind(AreaTour data) {
            contentTitleTextView.setText(data.getContentTitle());
            contentAddrTextView.setText(data.getAreaAddress());
            contentIntroTextView.setText(data.getOverview());
        }
    }
    class CourseViewHolder extends ViewHolder<TravelDetail>{
        private TextView contentCourseTitleTextView;
        private TextView contentCourseNumberTextView;
        private TextView contentCourseDescriptionTextView;
        private ImageView contentCourseImageTextView;

        public CourseViewHolder(View itemView) {
            super(itemView);
            contentCourseTitleTextView = itemView.findViewById(R.id.tour_course_title_text_view);
            contentCourseNumberTextView = itemView.findViewById(R.id.tour_course_number_text_view);
            contentCourseDescriptionTextView = itemView.findViewById(R.id.tour_course_description_text_view);
            contentCourseImageTextView = itemView.findViewById(R.id.tour_course_image_view);
        }

        @Override
        public void bind(TravelDetail data) {
            contentCourseTitleTextView.setText(data.getSubTitle());
            contentCourseNumberTextView.setText(String.valueOf(data.getSubNumber() + 1));
            contentCourseDescriptionTextView.setText( data.getSubDetailDescription());
            ImageLoderUtils.lodeURI(contentCourseImageTextView,data.getSubTitle());


        }
    }
    class InformationViewHolder extends ViewHolder<TravelInformation>{
        private TextView contentCourseTimeTextView;
        private TextView contentDistanceTextView;

        public InformationViewHolder(View itemView) {
            super(itemView);
            contentCourseTimeTextView = itemView.findViewById(R.id.tour_info_course_time_text_view);
            contentDistanceTextView = itemView.findViewById(R.id.tour_info_distance_text_view);
        }

        @Override
        public void bind(TravelInformation data) {
            if(data == null) return;
            contentCourseTimeTextView.setText(contentCourseTimeTextView.getText() + data.getCourseTime());
            contentDistanceTextView.setText(contentDistanceTextView.getText() + data.getDistance());
        }
    }

    abstract class ViewHolder<T> extends  RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
        abstract public void bind(T data);
    }
}
