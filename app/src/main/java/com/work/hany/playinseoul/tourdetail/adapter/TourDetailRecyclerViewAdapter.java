package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.model.dao.StayDetail;
import com.work.hany.playinseoul.model.dao.TourDetail;
import com.work.hany.playinseoul.model.dao.TourIntro;

import java.util.ArrayList;

import static com.work.hany.playinseoul.R.id;
import static com.work.hany.playinseoul.R.layout;

public class TourDetailRecyclerViewAdapter extends DetailRecyclerAdapter {


    public TourDetailRecyclerViewAdapter(ArrayList<Section> sections,  ItemListener itemListener) {
        super(sections,itemListener);
    }

    //TODO 리턴하는 null 이 마음에 안들어어엉
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Section.ItemType currentItemType = getCurrentItemType(viewType);

        switch (currentItemType) {
            case IMAGE:
                View imageRowView = inflater.inflate(R.layout.detail_recycler_row_image_itme, null, false);
                baseViewHolder = new ImageBaseViewHolder(imageRowView);
                break;

            case INFORMATION:
                View informationRowView = inflater.inflate(R.layout.detail_recycler_row_tour_intro_itme, null, false);
                baseViewHolder = new InformationBaseViewHolder(informationRowView);
                break;

            case OVERHEAD:
                View introRowView = inflater.inflate(R.layout.detail_recycler_row_overhead_itme, null, false);
                baseViewHolder = new OverHeadBaseViewHolder(introRowView);
                break;

            case DETAIL:
                View detailRowView = inflater.inflate(R.layout.detail_recycler_row_tour_detatil_itme, null, false);
                baseViewHolder = new TourDetailBaseViewHolder(detailRowView);
                break;

            case MAP:
                View mapRowView = inflater.inflate(R.layout.detail_recycler_row_tour_map_itme, null, false);
                baseViewHolder = new MapBaseViewHolder(mapRowView);
                break;

            case PHOTOS:
                View photoRowView = inflater.inflate(R.layout.detail_recycler_row_tour_photos_itme, null, false);
                baseViewHolder = new PhotosBaseViewHolder(photoRowView);
                break;


            case STAY_DETAIL:
                View stayDetail = inflater.inflate(layout.detail_recycler_row_stay_detatil_itme, null, false);
                baseViewHolder = new StayDetailBaseViewHolder(stayDetail);
                break;

        }

        return baseViewHolder;
    }


    class StayDetailBaseViewHolder extends BaseViewHolder<StayDetail> {
        private TextView basePeopleCountTextView;
        private TextView maxPeopleCountTextView;
        private TextView stayRoomNameTextView;
        private TextView stayRoomTitleTextView; //객실정보 타이틀
        private TextView stayOffSeasonFeeTextView;
        private TextView stayAllRoomShowTextView; //모든 객실 정보 보기
        private TextView stayMoreShowTextView; //해당객실정보 상세보기
        private final int MAX_SHOW_STAY_COUNT = 3;

        public StayDetailBaseViewHolder(View itemView) {
            super(itemView);
            //4개이상 나오면 모든 상품 보기 출력하자.
            basePeopleCountTextView = itemView.findViewById(id.stay_basecount_text_view);
            maxPeopleCountTextView = itemView.findViewById(id.stay_maxcount_text_view);
            stayRoomNameTextView = itemView.findViewById(id.stay_room_title_text_view);
            stayRoomTitleTextView = itemView.findViewById(id.stay_info_title_text_view);
            stayOffSeasonFeeTextView = itemView.findViewById(id.stay_offseasonmin_fee_text_view);
            stayMoreShowTextView = itemView.findViewById(id.stay_more_fee_text_view);
            stayAllRoomShowTextView = itemView.findViewById(id.stay_all_room_text_view);
        }

        @Override
        public void bind(StayDetail data) {
            if (data.getSerialNumber() == 0) stayRoomTitleTextView.setVisibility(View.VISIBLE);

            int maxShowRoomCount = MAX_SHOW_STAY_COUNT; //우리가 보여야 하는 값보다 작은 경우가 있다.
            if(data.getSerialMaxNumber() < maxShowRoomCount ) {
                maxShowRoomCount = data.getSerialMaxNumber();
            }

            stayRoomNameTextView.setText(data.getRoomTitle());
            String basePeopleCount = new StringBuffer().append("최소인원 ").append(data.getRoomBaseCount()).append("명").toString();
            String maxPeopleCount = new StringBuffer().append("최대인원 ").append(data.getRoomMaxCount()).append("명").toString();

            basePeopleCountTextView.setText(basePeopleCount);
            maxPeopleCountTextView.setText(maxPeopleCount);

            //TODO 금전 , 표시 작업하기
            String offSeasonFee = new StringBuffer().append("비수기 주중 최소 ").append(data.getRoomOffSeasonMinFee1()).append("원").toString();
            stayOffSeasonFeeTextView.setText(offSeasonFee);

            View betweenItemDivider = itemView.findViewById(id.divider1); //아이템간 디바이더
            if (data.getSerialNumber() == maxShowRoomCount) { //최대 4개의 아이템이 들어와서, 3개가 마지막인덱스가 된다.
                if (data.isRoomOverMaxCount()) {
                    stayAllRoomShowTextView.setVisibility(View.VISIBLE);
                } else {
                    betweenItemDivider.setVisibility(View.INVISIBLE);
                }

                itemView.findViewById(id.divider2).setVisibility(View.VISIBLE);
            }

        }
    }
    //https://developers.google.com/maps/documentation/android-sdk/map?authuser=1&hl=ko#the_map_object 맵ㅂ뷰 사용법

    class TourDetailBaseViewHolder extends BaseViewHolder<TourDetail> {
        private View sectionDividerView;
        private TextView contentTitleTextView;
        private TextView contentSubTitleTextView;
        private TextView contentSubDescriptionTextView;

        public TourDetailBaseViewHolder(View itemView) {
            super(itemView);
            contentSubTitleTextView = itemView.findViewById(id.tour_content_title_text_view);
            contentSubDescriptionTextView = itemView.findViewById(id.tour_content_description_text_view);
            contentTitleTextView = itemView.findViewById(id.tour_info_title_text_view);
            sectionDividerView = itemView.findViewById(id.divider);

        }

        @Override
        public void bind(TourDetail data) {
            try {
                if (data.getSerialNumber() == 0) { contentTitleTextView.setVisibility(View.VISIBLE); }
                if (data.getSerialNumber() == data.getSerialMaxNumber()) { sectionDividerView.setVisibility(View.VISIBLE); }
                contentSubTitleTextView.setText(data.getInformationTitle());
                SpannableString spannableString = new SpannableString(Html.fromHtml(data.getInformationDescription()));
                contentSubDescriptionTextView.setText(spannableString);

            } catch (NullPointerException e) {
                //TODO 각 데이터에 대하여 null exception 처리하자.
                Toast.makeText(itemView.getContext(), "Error: "+e.getMessage(),Toast.LENGTH_SHORT).show();
            }

        }

    }

    class InformationBaseViewHolder extends BaseViewHolder<TourIntro> {
        private LinearLayout informationLayout;

        public InformationBaseViewHolder(View itemView) {
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
}
