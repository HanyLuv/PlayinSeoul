package com.work.hany.playinseoul.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.work.hany.playinseoul.BaseSectionRecyclerAdapter;
import com.work.hany.playinseoul.R;
import com.work.hany.playinseoul.main.MainFragment;
import com.work.hany.playinseoul.model.ContentType;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.util.ConverterUtils;
import com.work.hany.playinseoul.util.ImageLoderUtils;
import com.work.hany.playinseoul.tourdetail.adapter.ViewHolder;

import java.util.ArrayList;


public class MainRecyclerViewAdapter extends BaseSectionRecyclerAdapter {
    private MainFragment.MainItemListener mainItemListener;


    public MainRecyclerViewAdapter(ArrayList<Section> sections, MainFragment.MainItemListener mainItemListener) {
        this.mainItemListener = mainItemListener;
        this.sections = sections;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Section.ItemType currentItemType = getCurrentItemType(viewType);
        View itemView;

        switch (currentItemType) {
            case CATEGORY:
                itemView = inflater.inflate(R.layout.main_recycler_row_category, parent, false);
                viewHolder = new CategoryViewHolder(itemView);
                break;
            case MAIN_TOUR:
                itemView = inflater.inflate(R.layout.main_recycler_row_item, parent, false);
                viewHolder = new TourViewHolder(itemView);
                break;

        }

        return viewHolder;
    }


    // https://irpdevelop.wordpress.com/2016/02/10/horizontal-recyclerview-inside-a-vertical-recyclerview/


    class CategoryViewHolder extends ViewHolder<ArrayList<ContentType>> {
        private RecyclerView catrgoryRecyclerView;
        private TextView categoryTitleView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTitleView = itemView.findViewById(R.id.main_tour_title_text_view);
            catrgoryRecyclerView = itemView.findViewById(R.id.main_tour_recycler_view);
        }

        @Override
        public void bind(ArrayList<ContentType> data) {
            CategoryHorizontalAdapter categoryHorizontalAdapter =new CategoryHorizontalAdapter(data);
            catrgoryRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            catrgoryRecyclerView.setAdapter(categoryHorizontalAdapter);

        }

        private class CategoryHorizontalAdapter extends RecyclerView.Adapter<CategoryItemViewHolder> {
            private int rowIndex = -1;
            private ArrayList<ContentType> categoryTypes;


            public CategoryHorizontalAdapter(ArrayList<ContentType> categoryTypes) {
                this.categoryTypes = categoryTypes;
            }

            @NonNull
            @Override
            public CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
               LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
                return new CategoryItemViewHolder(inflater.inflate(R.layout.main_recycler_row_category_item,null));
            }

            @Override
            public void onBindViewHolder(@NonNull CategoryItemViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return categoryTypes.size();
            }
        }


        private class CategoryItemViewHolder extends RecyclerView.ViewHolder {

            public CategoryItemViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    //    class Locate
    class TourViewHolder extends ViewHolder<AreaTour> {
        private ImageView tourImageView;
        private TextView tourTextView;
        private TextView tourContentTypeTextView;


        public TourViewHolder(View itemView) {
            super(itemView);
            tourImageView = itemView.findViewById(R.id.tourimage);
            tourTextView = itemView.findViewById(R.id.tourtitle);
            tourContentTypeTextView = itemView.findViewById(R.id.tourcontenttype);

        }

        @Override
        public void bind(final AreaTour areaTour) {
            ImageLoderUtils.lodeURI(tourImageView, areaTour.getLargeImage());
            tourTextView.setText(areaTour.getContentTitle());
            tourContentTypeTextView.setText(ConverterUtils.convertContentType(areaTour.getContentTypeId()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mainItemListener.onTourClick(areaTour);

                }
            });
        }
    }
}
