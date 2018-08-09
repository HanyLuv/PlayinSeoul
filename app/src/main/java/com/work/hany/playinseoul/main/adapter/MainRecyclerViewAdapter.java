package com.work.hany.playinseoul.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
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
    private RecyclerView.RecycledViewPool recycledViewPool;

    public MainRecyclerViewAdapter(ArrayList<Section> sections, MainFragment.MainItemListener mainItemListener) {
        this.mainItemListener = mainItemListener;
        this.sections = sections;
//        this.recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recycledViewPool = recyclerView.getRecycledViewPool();
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
                CategoryViewHolder categoryViewHolder = new CategoryViewHolder(itemView);
                categoryViewHolder.categoryRecyclerView.setRecycledViewPool(recycledViewPool);
                viewHolder = categoryViewHolder;
                break;

            case MAIN_TOUR:
                itemView = inflater.inflate(R.layout.main_recycler_row_tour, parent, false);
                TourViewHolder tourViewHolder = new TourViewHolder(itemView);
                tourViewHolder.tourSectionItemsRecyclerView.setRecycledViewPool(recycledViewPool);
                viewHolder = tourViewHolder;
                break;

        }

        return viewHolder;
    }

    private int categoryRecyclerViewScrollPosition = 0;

    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        super.onViewRecycled(holder);
        if(holder instanceof CategoryViewHolder) {
            CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
            RecyclerView categoryRecyclerView =  categoryViewHolder.categoryRecyclerView;
            categoryRecyclerViewScrollPosition =  categoryRecyclerView.computeHorizontalScrollOffset();

        }

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if(holder instanceof CategoryViewHolder) {
            CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
            categoryViewHolder.categoryRecyclerView.scrollBy(categoryRecyclerViewScrollPosition,0);
        }
    }

    // https://irpdevelop.wordpress.com/2016/02/10/horizontal-recyclerview-inside-a-vertical-recyclerview/


    class CategoryViewHolder extends ViewHolder<ArrayList<ContentType>> {
        private RecyclerView categoryRecyclerView;
        private TextView categoryTitleView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            categoryTitleView = itemView.findViewById(R.id.main_tour_title_text_view);
            categoryRecyclerView = itemView.findViewById(R.id.main_tour_recycler_view);
            categoryRecyclerView.setHasFixedSize(true);
        }

        @Override
        public void bind(ArrayList<ContentType> data) {
            CategoryHorizontalAdapter categoryHorizontalAdapter =new CategoryHorizontalAdapter(data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            categoryRecyclerView.setLayoutManager(layoutManager);
            categoryRecyclerView.setAdapter(categoryHorizontalAdapter);

        }

        private class CategoryHorizontalAdapter extends RecyclerView.Adapter<CategoryItemViewHolder> {
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
                holder.bind(categoryTypes.get(position));
            }

            @Override
            public int getItemCount() {
                return categoryTypes.size();
            }
        }


        private class CategoryItemViewHolder extends RecyclerView.ViewHolder {
            private TextView categoryTitleTextView;


            public CategoryItemViewHolder(View itemView) {
                super(itemView);
                categoryTitleTextView = itemView.findViewById(R.id.category_item_title_text_view);
            }

            public void bind(ContentType type){
                categoryTitleTextView.setText(type.getName());
            }
        }
    }

    class TourViewHolder extends ViewHolder<AreaTour> {
        private ImageView tourSectionImageView;
        private TextView tourSectionTitleTextView;
        private RecyclerView tourSectionItemsRecyclerView;


        public TourViewHolder(View itemView) {
            super(itemView);
            tourSectionImageView = itemView.findViewById(R.id.tour_content_image_view);
            tourSectionTitleTextView = itemView.findViewById(R.id.tour_section_title_text_view);
            tourSectionItemsRecyclerView = itemView.findViewById(R.id.tour_section_item_recycler_view);

        }

        @Override
        public void bind(final AreaTour areaTour) {
            ImageLoderUtils.lodeURI(tourSectionImageView, areaTour.getLargeImage());
            TourSectionItemsAdapter tourSectionItemsAdapter = new TourSectionItemsAdapter(new ArrayList<AreaTour>());
            tourSectionItemsRecyclerView.setLayoutManager(new GridLayoutManager( itemView.getContext(), 2));
            tourSectionItemsRecyclerView.setAdapter(tourSectionItemsAdapter);
        }

        private class TourSectionItemsAdapter extends RecyclerView.Adapter<TourSectionItemsAdapter.ItemViewHolder> {
            private ArrayList<AreaTour> areaTourList;

            public TourSectionItemsAdapter(ArrayList<AreaTour> areaTourList) {
                this.areaTourList = areaTourList;
            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
                return new TourSectionItemsAdapter.ItemViewHolder(inflater.inflate(R.layout.main_recycler_row_tour_item,null));
            }

            @Override
            public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 4;
            }

            class ItemViewHolder extends RecyclerView.ViewHolder {

                public ItemViewHolder(View itemView) {
                    super(itemView);
                }
            }

        }
    }

    //    class Locate
/*    class TourViewHolder extends ViewHolder<AreaTour> {
        private ImageView tourImageView;
        private TextView tourTextView;
        private TextView tourContentTypeTextView;


        public TourViewHolder(View itemView) {
            super(itemView);
            tourImageView = itemView.findViewById(R.id.tour_content_image_view);
            tourTextView = itemView.findViewById(R.id.tour_title_text_view);
            tourContentTypeTextView = itemView.findViewById(R.id.tour_content_type_text_view);

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
    }*/
}
