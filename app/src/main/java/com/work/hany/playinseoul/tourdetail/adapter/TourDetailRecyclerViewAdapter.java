package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.model.dao.TourIntro;
import com.work.hany.playinseoul.network.TourPhoto;

import java.util.ArrayList;

public class TourDetailRecyclerViewAdapter extends DetailRecyclerAdapter {


    public TourDetailRecyclerViewAdapter(ArrayList<Section> sections) {
        super(sections);
    }

    //TODO 리턴하는 null 이 마음에 안들어어엉
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        Section.ItemType currentItemType = getCurrentItemType(viewType);

        switch (currentItemType) {
            case IMAGE:
                View imageRowView= inflater.inflate(R.layout.detail_recycler_row_image_itme,null,false);
                viewHolder = new ImageViewHolder(imageRowView);
                break;

            case INFORMATION:
                View informationRowView= inflater.inflate(R.layout.detail_recycler_row_tour_intro_itme,null,false);
                viewHolder = new InformationViewHolder(informationRowView);
                break;

            case OVERHEAD:
                View introRowView= inflater.inflate(R.layout.detail_recycler_row_overhead_itme,null,false);
                viewHolder = new TravelCourseDetailRecyclerViewAdapter.OverHeadViewHolder(introRowView);
                break;

            case DETAIL:
                View detailRowView= inflater.inflate(R.layout.detail_recycler_row_tour_detatil_itme, null, false);
                viewHolder = new TourDetailViewHolder(detailRowView);
                break;

            case MAP:
                View mapRowView= inflater.inflate(R.layout.detail_recycler_row_tour_map_itme, null, false);
                viewHolder = new MapViewHolder(mapRowView);
                break;

            case PHOTOS:
                View photoRowView= inflater.inflate(R.layout.detail_recycler_row_tour_photos_itme, null, false);
                viewHolder = new PhotosViewHolder(photoRowView);
                break;

        }

        return viewHolder;
    }

    class MapViewHolder extends ViewHolder<ArrayList<TourPhoto>>{
        public MapViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(ArrayList<TourPhoto> tourPhotos) {

        }
    }
    class TourDetailViewHolder extends ViewHolder<TourDetail> {
        private TextView contentTitleTextView;
        private TextView contentSubTitleTextView;
        private TextView contentSubDescriptionTextView;
        public TourDetailViewHolder(View itemView) {
            super(itemView);
            contentSubTitleTextView = itemView.findViewById(R.id.tour_content_title_text_view);
            contentSubDescriptionTextView = itemView.findViewById(R.id.tour_content_description_text_view);
            contentTitleTextView = itemView.findViewById(R.id.tour_info_title_text_view);

        }
        @Override
        public void bind(TourDetail data) {
            if(data.getSerialNumber() == 0) { contentTitleTextView.setVisibility(View.VISIBLE); }
            contentSubTitleTextView.setText(data.getInformationTitle());
            contentSubDescriptionTextView.setText(data.getInformationDescription());


        }
    }

    class InformationViewHolder extends ViewHolder<TourIntro>{
        private LinearLayout informationLayout;
        public InformationViewHolder(View itemView) {
            super(itemView);
            informationLayout = itemView.findViewById(R.id.tour_info_layout);
        }

        @Override
        public void bind(TourIntro tourIntros) {
            /*
            *
            *             for (TourIntro intro : tourIntros ) {
                TextView informationText = new TextView(itemView.getContext());
                informationText.setText(intro.get);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = params.rightMargin = (int) DisplayUtils.dpToPx(35f);
                params.bottomMargin = (int) DisplayUtils.dpToPx(5f);
                informationText.setLayoutParams(params);

                informationLayout.addView(informationText);
            }
            * */

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


}
