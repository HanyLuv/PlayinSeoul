package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.TravelDetail;
import com.work.hany.playinseoul.network.TravelIntro;
import com.work.hany.playinseoul.util.ImageLoderUtils;

import java.util.ArrayList;

import static com.work.hany.playinseoul.model.Section.ItemType;

public class TravelCourseDetailRecyclerViewAdapter extends DetailRecyclerAdapter {

    private TravelCourseDetailRecyclerViewAdapter.TravelCourseItemListener travelCourseItemListener;

    public interface TravelCourseItemListener {
        void onSubCourseDetailShowClicked(TravelDetail travelDetail);
    }

    public TravelCourseDetailRecyclerViewAdapter(ArrayList<Section> sections, TravelCourseDetailRecyclerViewAdapter.ItemListener itemListener) {
        super(sections, itemListener);
        this.itemListener = itemListener;
    }

    public void setTravelCourseItemListener(TravelCourseItemListener travelCourseItemListener) {
        this.travelCourseItemListener = travelCourseItemListener;
    }

    //
    // TODO 공통으로 보이는 아이템 셀 부분이 많은데 이걸 한곳에 묶고싶어 ㅠㅠㅠ 생각해보자

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemType currentItemType = getCurrentItemType(viewType);

        switch (currentItemType) {
            case IMAGE:
                View imageRowView = inflater.inflate(R.layout.detail_recycler_row_image_itme, null, false);
                baseViewHolder = new ImageBaseViewHolder(imageRowView);
                break;

            case INFORMATION:
                View informationRowView = inflater.inflate(R.layout.detail_recycler_row_travel_tour_intro_itme, null, false);
                baseViewHolder = new InformationBaseViewHolder(informationRowView);
                break;

            case OVERHEAD:
                View introRowView = inflater.inflate(R.layout.detail_recycler_row_overhead_itme, null, false);
                baseViewHolder = new OverHeadBaseViewHolder(introRowView);
                break;

            case DETAIL:
                View courseView = inflater.inflate(R.layout.detail_recycler_row_course_itme, null, false);
                baseViewHolder = new CourseBaseViewHolder(courseView);
                break;


        }

        return baseViewHolder;
    }


    class CourseBaseViewHolder extends BaseViewHolder<TravelDetail> {
        private TextView contentCourseTitleTextView;
        private TextView contentCourseNumberTextView;
        private TextView contentCourseDescriptionTextView;
        private ImageView contentCourseImageTextView;
        private TextView contentCourseMoreTextView;

        public CourseBaseViewHolder(View itemView) {
            super(itemView);
            contentCourseTitleTextView = itemView.findViewById(R.id.tour_course_title_text_view);
            contentCourseNumberTextView = itemView.findViewById(R.id.tour_course_number_text_view);
            contentCourseDescriptionTextView = itemView.findViewById(R.id.tour_course_description_text_view);
            contentCourseImageTextView = itemView.findViewById(R.id.tour_course_image_view);
            contentCourseMoreTextView = itemView.findViewById(R.id.tour_course_more_detail_text_view);

        }

        @Override
        public void bind(final TravelDetail data) {
            contentCourseTitleTextView.setText(data.getSubTitle());
            contentCourseNumberTextView.setText(String.valueOf(data.getSubNumber() + 1));
            contentCourseDescriptionTextView.setText(data.getSubDetailDescription());
            ImageLoderUtils.lodeURI(contentCourseImageTextView, data.getSubDetailImage());
            contentCourseMoreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (travelCourseItemListener != null) {
                        travelCourseItemListener.onSubCourseDetailShowClicked(data);
                    }
                }

            });


        }
    }

    class InformationBaseViewHolder extends BaseViewHolder<TravelIntro> {
        private TextView contentCourseTimeTextView;
        private TextView contentDistanceTextView;

        public InformationBaseViewHolder(View itemView) {
            super(itemView);
            contentCourseTimeTextView = itemView.findViewById(R.id.tour_info_course_time_text_view);
            contentDistanceTextView = itemView.findViewById(R.id.tour_info_distance_text_view);
        }

        @Override
        public void bind(TravelIntro data) {
            if (data == null) return;
            String courseTimeString = new StringBuilder().append("소요시간 ").append(data.getCourseTime()).toString();
            String distanceString = new StringBuilder().append("총 거리 ").append(data.getDistance()).toString();
            contentCourseTimeTextView.setText(courseTimeString);
            contentDistanceTextView.setText(distanceString);
        }
    }

}
