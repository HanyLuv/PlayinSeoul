package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.util.ConverterUtils;
import com.work.hany.playinseoul.util.ImageLoderUtils;

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

        public OverHeadViewHolder(View itemView) {
            super(itemView);
            contentIntroTextView = itemView.findViewById(R.id.tour_information_text_view);
            contentTitleTextView = itemView.findViewById(R.id.tour_title_text_view);
            contentAddrTextView = itemView.findViewById(R.id.tour_addr_text_view);
        }

        @Override
        public void bind(AreaTour data) {
            contentAddrTextView.setText(ConverterUtils.convertMediumCategory(data.getMediumCategoryCode()));
            contentTitleTextView.setText(data.getContentTitle());
            contentIntroTextView.setText(data.getOverview());
        }
    }


}