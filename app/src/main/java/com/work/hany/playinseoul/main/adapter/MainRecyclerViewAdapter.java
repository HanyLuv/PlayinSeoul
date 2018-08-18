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
import com.work.hany.playinseoul.model.ContentType;
import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.network.AreaTour;
import com.work.hany.playinseoul.tourdetail.adapter.BaseViewHolder;
import com.work.hany.playinseoul.util.ConverterUtils;
import com.work.hany.playinseoul.util.ImageLoderUtils;

import java.util.ArrayList;


public class MainRecyclerViewAdapter extends BaseSectionRecyclerAdapter {
    private MainRecyclerViewAdapter.ItemListener mainItemListener;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public interface ItemListener {
        void onTourClicked(AreaTour tour);
        void onMoreTourClicked(AreaTour tour);
        void onCategoryClicked(ContentType type);
    }


    public MainRecyclerViewAdapter(ArrayList<Section> sections, MainRecyclerViewAdapter.ItemListener mainItemListener) {
        this.mainItemListener = mainItemListener;
        this.sections = sections;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recycledViewPool = recyclerView.getRecycledViewPool();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Section.ItemType currentItemType = getCurrentItemType(viewType);
        View itemView;

        switch (currentItemType) {
            case CATEGORY:
                itemView = inflater.inflate(R.layout.main_recycler_row_category, parent, false);
                CategoryBaseViewHolder categoryViewHolder = new CategoryBaseViewHolder(itemView);
                categoryViewHolder.categoryRecyclerView.setRecycledViewPool(recycledViewPool);
                baseViewHolder = categoryViewHolder;
                break;

            case MAIN_TOUR:
                itemView = inflater.inflate(R.layout.main_recycler_row_tour, parent, false);
                TourSectionBaseViewHolder tourSectionViewHolder = new TourSectionBaseViewHolder(itemView);
                tourSectionViewHolder.tourSectionItemsRecyclerView.setRecycledViewPool(recycledViewPool);
                baseViewHolder = tourSectionViewHolder;
                break;

        }

        return baseViewHolder;
    }

    private int categoryRecyclerViewScrollPosition = 0;

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        if (Section.ItemType.CATEGORY.getCode() == holder.getItemViewType()) {
            CategoryBaseViewHolder categoryViewHolder = CategoryBaseViewHolder.class.cast(holder);
            RecyclerView categoryRecyclerView = categoryViewHolder.categoryRecyclerView;
            categoryRecyclerViewScrollPosition = categoryRecyclerView.computeHorizontalScrollOffset();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (Section.ItemType.CATEGORY.getCode() == holder.getItemViewType()) {
            CategoryBaseViewHolder categoryViewHolder = CategoryBaseViewHolder.class.cast(holder);
            categoryViewHolder.categoryRecyclerView.scrollBy(categoryRecyclerViewScrollPosition, 0);
        }
    }

    /**
     * 투어 섹션 뷰홀더
     */
    private class TourSectionBaseViewHolder extends BaseViewHolder<ArrayList<AreaTour>> {
        private ImageView tourSectionImageView;
        private TextView tourSectionMoreTextView;
        private TextView tourSectionTitleTextView;
        private RecyclerView tourSectionItemsRecyclerView;


        public TourSectionBaseViewHolder(View itemView) {
            super(itemView);
            tourSectionImageView = itemView.findViewById(R.id.tour_content_image_view);
            tourSectionMoreTextView = itemView.findViewById(R.id.tour_more_text_view);
            tourSectionTitleTextView = itemView.findViewById(R.id.tour_section_title_text_view);
            tourSectionItemsRecyclerView = itemView.findViewById(R.id.tour_section_item_recycler_view);

        }

        @Override
        public void bind(final ArrayList<AreaTour> areaTour) {
            String sectionTitle = ConverterUtils.convertContentType(areaTour.get(0).getContentTypeId());
            tourSectionTitleTextView.setText(sectionTitle);

            ImageLoderUtils.lodeURI(tourSectionImageView, areaTour.get(areaTour.size() - 1).getLargeImage());

            TourSectionItemsAdapter tourSectionItemsAdapter = new TourSectionItemsAdapter(areaTour);
            tourSectionItemsRecyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(), 2));
            tourSectionItemsRecyclerView.setAdapter(tourSectionItemsAdapter);

            tourSectionMoreTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    mainItemListener.onMoreTourClicked(areaTour.get(0));
                }
            });
        }

        /**
         * 투어 섹션 아이템 어댑터
         */
        private class TourSectionItemsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
            private ArrayList<AreaTour> areaTourList;
            private final int SECTION_IMAGE_COUNT = 1;

            public TourSectionItemsAdapter(ArrayList<AreaTour> areaTourList) {
                this.areaTourList = areaTourList;
            }

            @NonNull
            @Override
            public TourItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                TourItemViewHolder tourItemViewHolder = new TourItemViewHolder(inflater.inflate(R.layout.main_recycler_row_tour_item, null));
                return tourItemViewHolder;
            }

            @Override
            public int getItemViewType(int position) {
                return Section.ItemType.MAIN_TOUR.getCode();
            }

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                if (holder.getItemViewType() != Section.ItemType.MAIN_TOUR.getCode()) {
                    RecyclerView.ViewHolder holderFromRecyclerPool = tourSectionItemsRecyclerView.getRecycledViewPool().getRecycledView(getItemViewType(position));
                    ((TourItemViewHolder) holderFromRecyclerPool).bind(areaTourList.get(holder.getAdapterPosition()));
                } else {
                    holder.bind(areaTourList.get(holder.getAdapterPosition()));
                }
            }

            @Override
            public int getItemCount() {
                return areaTourList.size() - SECTION_IMAGE_COUNT;
            }
        }
        /**
         * 투어 섹션 아이템 뷰홀더
         */
        private class TourItemViewHolder extends BaseViewHolder<AreaTour> {
            private ImageView tourImageView;
            private TextView tourTextView;
            private TextView tourAddrTextView;
            private TextView tourContentShowCountTextView;

            public TourItemViewHolder(View itemView) {
                super(itemView);
                tourImageView = itemView.findViewById(R.id.tour_content_image_view);
                tourTextView = itemView.findViewById(R.id.tour_title_text_view);
                tourContentShowCountTextView = itemView.findViewById(R.id.tour_show_text_view);
                tourAddrTextView = itemView.findViewById(R.id.tour_content_addr_text_view);

            }

            public void bind(final AreaTour tour) {
                ImageLoderUtils.lodeURI(tourImageView, tour.getLargeImage());
                tourTextView.setText(tour.getContentTitle());

                String countStr = new StringBuilder().append("조회수 ").append(tour.getReadCount()).toString();
                tourContentShowCountTextView.setText(countStr);
                tourAddrTextView.setText(tour.getFullAddress());

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainItemListener.onTourClicked(tour);
                    }
                });

            }
        }

    }


    /**
     * 키테고리 뷰홀더
     */

    private class CategoryBaseViewHolder extends BaseViewHolder<ArrayList<ContentType>> {
        private RecyclerView categoryRecyclerView;

        public CategoryBaseViewHolder(View itemView) {
            super(itemView);
//            categoryTitleView = itemView.findViewById(R.id.main_tour_title_text_view);
            categoryRecyclerView = itemView.findViewById(R.id.main_tour_recycler_view);
            categoryRecyclerView.setHasFixedSize(true);
        }

        @Override
        public void bind(ArrayList<ContentType> data) {
            CategoryHorizontalAdapter categoryHorizontalAdapter = new CategoryHorizontalAdapter(data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            categoryRecyclerView.setLayoutManager(layoutManager);
            categoryRecyclerView.setAdapter(categoryHorizontalAdapter);

        }


        /**
         * 키테고리 아이템 어댑터
         */
        private class CategoryHorizontalAdapter extends RecyclerView.Adapter<BaseViewHolder> {
            private ArrayList<ContentType> categoryTypes;

            public CategoryHorizontalAdapter(ArrayList<ContentType> categoryTypes) {
                this.categoryTypes = categoryTypes;
            }

            @Override
            public int getItemViewType(int position) {
                return Section.ItemType.CATEGORY.getCode();
            }

            @NonNull
            @Override
            public CategoryItemBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                CategoryItemBaseViewHolder categoryItemBaseViewHolder = new CategoryItemBaseViewHolder(inflater.inflate(R.layout.main_recycler_row_category_item, null));
                return categoryItemBaseViewHolder;
            }

            @Override
            public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
                if (holder.getItemViewType() != Section.ItemType.CATEGORY.getCode()) {
                    RecyclerView.ViewHolder holderFromRecyclerPool = categoryRecyclerView.getRecycledViewPool().getRecycledView(getItemViewType(position));
                    ((CategoryItemBaseViewHolder) holderFromRecyclerPool).bind(categoryTypes.get(holder.getAdapterPosition()));
                } else {
                    holder.bind(categoryTypes.get(holder.getAdapterPosition()));
                }
            }

            @Override
            public int getItemCount() {
                return categoryTypes.size();
            }
        }

        /**
         * 키테고리 아이템 뷰 홀더
         */
        private class CategoryItemBaseViewHolder extends BaseViewHolder<ContentType> {
            private TextView categoryTitleTextView;


            public CategoryItemBaseViewHolder(View itemView) {
                super(itemView);
                categoryTitleTextView = itemView.findViewById(R.id.category_item_title_text_view);
            }

            public void bind(final ContentType type) {
                categoryTitleTextView.setText(type.getTagName());
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mainItemListener.onCategoryClicked(type);
                    }
                });
            }
        }
    }
}

