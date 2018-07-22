package com.work.hany.playinseoul.tourdetail.adapter;

import android.app.Fragment;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.model.dao.TourIntro;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.util.ImageLoderUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.work.hany.playinseoul.R.*;

public class TourDetailRecyclerViewAdapter extends DetailRecyclerAdapter {


    public TourDetailRecyclerViewAdapter(ArrayList<Section> sections) {
        super(sections);
    }

    //TODO 리턴하는 null 이 마음에 안들어어엉
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Section.ItemType currentItemType = getCurrentItemType(viewType);

        switch (currentItemType) {
            case IMAGE:
                View imageRowView = inflater.inflate(R.layout.detail_recycler_row_image_itme, null, false);
                viewHolder = new ImageViewHolder(imageRowView);
                break;

            case INFORMATION:
                View informationRowView = inflater.inflate(R.layout.detail_recycler_row_tour_intro_itme, null, false);
                viewHolder = new InformationViewHolder(informationRowView);
                break;

            case OVERHEAD:
                View introRowView = inflater.inflate(R.layout.detail_recycler_row_overhead_itme, null, false);
                viewHolder = new TravelCourseDetailRecyclerViewAdapter.OverHeadViewHolder(introRowView);
                break;

            case DETAIL:
                View detailRowView = inflater.inflate(R.layout.detail_recycler_row_tour_detatil_itme, null, false);
                viewHolder = new TourDetailViewHolder(detailRowView);
                break;

            case MAP:
                View mapRowView = inflater.inflate(R.layout.detail_recycler_row_tour_map_itme, null, false);
                viewHolder = new MapViewHolder(mapRowView);
                break;

            case PHOTOS:
                View photoRowView = inflater.inflate(R.layout.detail_recycler_row_tour_photos_itme, null, false);
                viewHolder = new PhotosViewHolder(photoRowView);
                break;

        }

        return viewHolder;
    }
    //https://developers.google.com/maps/documentation/android-sdk/map?authuser=1&hl=ko#the_map_object 맵ㅂ뷰 사용법

    class TourDetailViewHolder extends ViewHolder<TourDetail> {
        private TextView contentTitleTextView;
        private TextView contentSubTitleTextView;
        private TextView contentSubDescriptionTextView;

        public TourDetailViewHolder(View itemView) {
            super(itemView);
            contentSubTitleTextView = itemView.findViewById(id.tour_content_title_text_view);
            contentSubDescriptionTextView = itemView.findViewById(id.tour_content_description_text_view);
            contentTitleTextView = itemView.findViewById(id.tour_info_title_text_view);

        }

        @Override
        public void bind(TourDetail data) {
            if (data.getSerialNumber() == 0) {
                contentTitleTextView.setVisibility(View.VISIBLE);
            }
            contentSubTitleTextView.setText(data.getInformationTitle());
            contentSubDescriptionTextView.setText(data.getInformationDescription());


        }
    }

    class InformationViewHolder extends ViewHolder<TourIntro> {
        private LinearLayout informationLayout;

        public InformationViewHolder(View itemView) {
            super(itemView);
            informationLayout = itemView.findViewById(id.tour_info_layout);
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

    class PhotosViewHolder extends ViewHolder<ArrayList<TourPhoto>> {
        private LinearLayout tourPhotosLayout;
        private LayoutInflater inflater;
        private final int MAX_SHOW_IMAGE_COUNT = 3;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            inflater = LayoutInflater.from(itemView.getContext());
            tourPhotosLayout = itemView.findViewById(R.id.tour_photo_layout);
        }

        @Override
        public void bind(ArrayList<TourPhoto> tourPhotos) {
            for (int index = 0, endIndex = tourPhotos.size(); index <= MAX_SHOW_IMAGE_COUNT; ) {
                View photoLayout = inflater.inflate(R.layout.detail_recycler_row_tour_photo_itme, null, false);

                ImageView leftImageView = photoLayout.findViewById(id.tour_left_photo_image_view);
                ImageView rightImageView = photoLayout.findViewById(id.tour_right_photo_image_view);

                int nextIndex = index + 1;
                String leftImageUrl = tourPhotos.get(index).getOriginImageURI();
                String rightImageUrl = "";

                if (nextIndex < endIndex) {
                    rightImageUrl = tourPhotos.get(nextIndex).getOriginImageURI();

                    if (nextIndex == MAX_SHOW_IMAGE_COUNT) {
                        boolean hasNextIndex = (nextIndex + 1) < endIndex;
                        if (hasNextIndex) {
                            String moreText = new StringBuilder().append("+").append(endIndex - MAX_SHOW_IMAGE_COUNT).toString();
                            TextView morePhotoTextView = photoLayout.findViewById(R.id.tour_photo_more_text_view);
                            morePhotoTextView.setVisibility(View.VISIBLE);
                            morePhotoTextView.setText(moreText);
                        }


                    }
                }

                ImageLoderUtils.lodeURI(leftImageView, leftImageUrl);
                if (!rightImageUrl.isEmpty()) {
                    ImageLoderUtils.lodeURI(rightImageView, rightImageUrl);
                }
                index = nextIndex + 1;
                tourPhotosLayout.addView(photoLayout);


            }
        }


    }
}
