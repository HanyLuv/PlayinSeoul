package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.SpannableString;
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
import com.work.hany.playinseoul.BaseSectionRecyclerAdapter;
import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.network.TourPhoto;
import com.work.hany.playinseoul.util.ConverterUtils;
import com.work.hany.playinseoul.util.ImageLoderUtils;
import com.work.hany.playinseoul.util.StringUtils;

import java.util.ArrayList;

abstract public class DetailRecyclerAdapter extends BaseSectionRecyclerAdapter {
    protected ItemListener itemListener; //공통 아이템 리스너임..

    public interface ItemListener {
        void onOverViewMoreClicked(AreaTour tour);
        void onMapMoreClicked(AreaTour tour);
    }

    public DetailRecyclerAdapter(ArrayList<Section> sections, ItemListener itemListener){
        this.sections = sections;
        this.itemListener = itemListener;
    }


    //TODO getCurrentItemType 와 onCreateViewHolder 가 null을 리턴하는 방식이 마음에 안든당..생각해보자.

    protected class ImageBaseViewHolder extends BaseViewHolder<String> {
        private ImageView imageView;
        public ImageBaseViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.detail_tour_image);
        }

        @Override
        public void bind(String url) {
            ImageLoderUtils.lodeURI(imageView, url);
        }

    }

    protected class OverHeadBaseViewHolder extends BaseViewHolder<AreaTour> {
        private TextView contentIntroTextView;
        private TextView contentTitleTextView;
        private TextView contentAddrTextView;
        private TextView contentMoreTextView;
        private final int MAX_SHOW_INTRO_COUNT = 300; // 보여지는 인트로 문자 갯수. 300바이트 즉 한글일경우 150자정도...

        public OverHeadBaseViewHolder(View itemView) {
            super(itemView);
            contentIntroTextView = itemView.findViewById(R.id.tour_information_text_view);
            contentTitleTextView = itemView.findViewById(R.id.tour_title_text_view);
            contentAddrTextView = itemView.findViewById(R.id.tour_addr_text_view);
            contentMoreTextView = itemView.findViewById(R.id.tour_more_text_view);

        }

        @Override
        public void bind(final AreaTour data) {
            if(data.getMediumCategoryCode()!= null){ //TODO 카테고리값이... 리스트조회때만 불러오는거다보니 ㅠ 주소를 넣는게 맞을듯싶다. 에혀
                contentAddrTextView.setText(ConverterUtils.convertMediumCategory(data.getMediumCategoryCode()));
                contentAddrTextView.setVisibility(View.VISIBLE);
            }
            contentTitleTextView.setText(data.getContentTitle());

            if (!data.getOverview().isEmpty()) {
                SpannableString spannableOverViewString = new SpannableString(Html.fromHtml(data.getOverview()));
                String overView = spannableOverViewString.toString();

                if (spannableOverViewString.toString().trim().getBytes().length > MAX_SHOW_INTRO_COUNT) {
                    String splitOverViewString = StringUtils.SplitStringByByteLength(spannableOverViewString.toString(),"EUC-KR",MAX_SHOW_INTRO_COUNT)[0];
                    overView = new StringBuilder().append(splitOverViewString).append("...").toString();
                    contentMoreTextView.setVisibility(View.VISIBLE);
                    contentMoreTextView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            itemListener.onOverViewMoreClicked(data);
                        }
                    });
                }


                contentIntroTextView.setText(overView);

            }

        }
    }

    //아래 메소드 알아보기
    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);

        if( holder instanceof MapBaseViewHolder) {
            MapBaseViewHolder mapViewHolder = (MapBaseViewHolder)holder;
            if (mapViewHolder != null && mapViewHolder.map != null) {
                mapViewHolder.map.clear();
                mapViewHolder.map.setMapType(GoogleMap.MAP_TYPE_NONE);
            }
        }

    }

    protected class MapBaseViewHolder extends BaseViewHolder<AreaTour> implements OnMapReadyCallback {
        public MapView mapView;
        public GoogleMap  map;
        private TextView mapAddrTextView;
        private TextView mapMoreTextView;

        public MapBaseViewHolder(View itemView) {
            super(itemView);
            mapAddrTextView = itemView.findViewById(R.id.tour_addr_text_view);
            mapMoreTextView = itemView.findViewById(R.id.tour_map_more_text_view);
            mapView = itemView.findViewById(R.id.tour_map_view);
            if(mapView !=null) {
                mapView.onCreate(null);
                mapView.onResume();
                mapView.getMapAsync(this);
            }
        }

        @Override
        public void bind(final AreaTour areaTour) {
            if (areaTour == null) return;
            mapAddrTextView.setText(areaTour.getFullAddress());
            mapView.setTag(areaTour);
            setMapLocation();

            mapMoreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.onMapMoreClicked(areaTour);
                }
            });
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            MapsInitializer.initialize(itemView.getContext());
            map = googleMap;
            GoogleMapOptions options = new GoogleMapOptions().liteMode(true);
            map.setMapType(options.getMapType());
            map.getUiSettings().setAllGesturesEnabled(false);

            setMapLocation();
        }

        private void setMapLocation(){
            if (map == null) return;

            AreaTour areaTour = (AreaTour) mapView.getTag();
            if (areaTour == null) return;

            LatLng latLng = new LatLng(areaTour.getMapy(), areaTour.getMapx());

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));
            map.addMarker(new MarkerOptions().position(latLng));

            map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        //https://gist.github.com/alunsford3/5d7c1bb5a67b90b4e1f3
        //https://stackoverflow.com/questions/41915317/access-google-map-from-onbindviewholder-android

//        @Override
//        public void onMapReady(GoogleMap googleMap) {
//            googleMap.getUiSettings().setAllGesturesEnabled(false);
//            GoogleMapOptions options = new GoogleMapOptions().liteMode(true);
//            googleMap.setMapType(options.getMapType());
//
//            if (getAdapterPosition() > -1) { // -1 empty.
//                AreaTour areaTour = (AreaTour) sections.get(getAdapterPosition()).getData();
//
//                LatLng latLng = new LatLng(areaTour.getMapy(), areaTour.getMapx());
//                googleMap.addMarker(new MarkerOptions().position(latLng));
//                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//            } else {
//                Log.d("HANY_TAG", "" + getAdapterPosition());
//            }
//
//
//        }
    }


    /** 둘러보기, 음식점메뉴에 사용됨*/
    class PhotosBaseViewHolder extends BaseViewHolder<ArrayList<TourPhoto>> {
        private LinearLayout tourPhotosLayout;
        private LayoutInflater inflater;
        private final int MAX_SHOW_IMAGE_COUNT = 4;

        public PhotosBaseViewHolder(View itemView) {
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
