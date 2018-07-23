package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.util.ConverterUtils;
import com.work.hany.playinseoul.util.ImageLoderUtils;
import com.work.hany.playinseoul.util.StringUtils;

import java.util.ArrayList;

import static com.work.hany.playinseoul.model.Section.ItemType.NOTHING;

abstract public class DetailRecyclerAdapter extends RecyclerView.Adapter<ViewHolder>{
    protected ArrayList<Section> sections;

    public DetailRecyclerAdapter(ArrayList<Section> sections){
        this.sections = sections;
    }

//    // 파라미터명을 뭐라해야할지 모르겟네. 입력된 타입의 다음구간에 add한다고 보면된다.
//    public <T>void addSection(Section.ItemType whereType, Section.ItemType type, T data ){
//        for ( Section section : sections){
//            if (section.getType().equals(whereType) ) {
//                int index = sections.indexOf(section);
//                sections.add(index, new Section(type,data));
//                break;
//            }
//        }
//    }
//
    public <T>void addSection(Section.ItemType type, T data){
        sections.add(new Section(type,data));
        notifyDataSetChanged();
    }

    public <T>void updateSection(Section.ItemType type, T data) {
        for(int position = 0, end = sections.size(); position < end; position++ ){
            if (sections.get(position).getType().equals(type)) {
                sections.get(position).setData(data);
                notifyItemChanged(position);
            }
        }
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

    //TODO getCurrentItemType 와 onCreateViewHolder 가 null을 리턴하는 방식이 마음에 안든당..생각해보자.

    protected Section.ItemType getCurrentItemType(int position) {
        for (Section.ItemType itemType : Section.ItemType.values()) {
            if (itemType.getCode() == position) return itemType;
        }

        return NOTHING;
    }

    protected class ImageViewHolder extends ViewHolder<String> {
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

    protected class OverHeadViewHolder extends ViewHolder<AreaTour> {
        private TextView contentIntroTextView;
        private TextView contentTitleTextView;
        private TextView contentAddrTextView;
        private TextView contentMoreTextView;
        private final int MAX_SHOW_INTRO_COUNT = 300; // 보여지는 인트로 문자 갯수. 300바이트 즉 한글일경우 150자정도...

        public OverHeadViewHolder(View itemView) {
            super(itemView);
            contentIntroTextView = itemView.findViewById(R.id.tour_information_text_view);
            contentTitleTextView = itemView.findViewById(R.id.tour_title_text_view);
            contentAddrTextView = itemView.findViewById(R.id.tour_addr_text_view);
            contentMoreTextView = itemView.findViewById(R.id.tour_more_text_view);

        }

        @Override
        public void bind(final AreaTour data) {
            contentAddrTextView.setText(ConverterUtils.convertMediumCategory(data.getMediumCategoryCode()));
            contentTitleTextView.setText(data.getContentTitle());
            if (!data.getOverview().isEmpty()) {
                String overView = data.getOverview();

                if (overView.trim().getBytes().length > MAX_SHOW_INTRO_COUNT) {
                    overView = new StringBuilder().append( StringUtils.SplitStringByByteLength(overView,"EUC-KR",MAX_SHOW_INTRO_COUNT)[0]).append("...").toString();
                    contentMoreTextView.setVisibility(View.VISIBLE);

                }
                contentIntroTextView.setText(overView);

            }

        }
    }

    //아래 메소드 알아보기
    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
    }

    protected class MapViewHolder extends ViewHolder<AreaTour> implements OnMapReadyCallback {
        private MapView mapView;
        private TextView mapAddrTextView;

        public MapViewHolder(View itemView) {
            super(itemView);
            mapView = itemView.findViewById(R.id.tour_map_view);
            mapAddrTextView = itemView.findViewById(R.id.tour_addr_text_view);

        }

        @Override
        public void bind(AreaTour areaTour) {
            MapsInitializer.initialize(itemView.getContext());
            mapAddrTextView.setText(areaTour.getFullAddress());
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);

        }
        //https://gist.github.com/alunsford3/5d7c1bb5a67b90b4e1f3
        //https://stackoverflow.com/questions/41915317/access-google-map-from-onbindviewholder-android

        @Override
        public void onMapReady(GoogleMap googleMap) {
            googleMap.getUiSettings().setAllGesturesEnabled(false);
            GoogleMapOptions options = new GoogleMapOptions().liteMode(true);
//            options.maxZoomPreference()
            googleMap.setMapType(options.getMapType());

            if (getAdapterPosition() > -1) { // -1 empty.
                AreaTour areaTour = (AreaTour) sections.get(getAdapterPosition()).getData();

                LatLng latLng = new LatLng(areaTour.getMapy(), areaTour.getMapx());
                googleMap.addMarker(new MarkerOptions().position(latLng));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            } else {
                Log.d("HANY_TAG", "" + getAdapterPosition());
            }


        }
    }


    /** 둘러보기, 음식점메뉴에 사용됨*/
    class PhotosViewHolder extends ViewHolder<ArrayList<TourPhoto>> {
        private LinearLayout tourPhotosLayout;
        private LayoutInflater inflater;
        private final int MAX_SHOW_IMAGE_COUNT = 4;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            inflater = LayoutInflater.from(itemView.getContext());
            tourPhotosLayout = itemView.findViewById(R.id.tour_photo_layout);
        }

        @Override
        public void bind(ArrayList<TourPhoto> tourPhotos) {
            tourPhotosLayout.removeAllViews();
            int maxIndex =  MAX_SHOW_IMAGE_COUNT;
            if( maxIndex > tourPhotos.size() ) {
                maxIndex =  tourPhotos.size();
            }

            for (int index = 0, endIndex = tourPhotos.size(); index < maxIndex; ) {
                View photoLayout = inflater.inflate(R.layout.detail_recycler_row_tour_photo_itme, null, false);
                photoLayout.setTag(index);
                ImageView leftImageView = photoLayout.findViewById(R.id.tour_left_photo_image_view);
                ImageView rightImageView = photoLayout.findViewById(R.id.tour_right_photo_image_view);

                int nextIndex = index + 1;
                String leftImageUrl = tourPhotos.get(index).getOriginImageURI();
                String rightImageUrl = "";

                if (nextIndex < endIndex) {
                    rightImageUrl = tourPhotos.get(nextIndex).getOriginImageURI();

                    int lastIndex = maxIndex - 1;
                    if (nextIndex == lastIndex) {
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
