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
        /**
         * TODO 아래로그 확인후 잡자 졸려서 일단 자야할듯 흐규
         *
         *     java.lang.ClassCastException: com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter$TourViewHolder$TourSectionItemsAdapter$ItemViewHolder cannot be cast to com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter$CategoryViewHolder$CategoryItemViewHolder
         at com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter$CategoryViewHolder$CategoryHorizontalAdapter.onBindViewHolder(MainRecyclerViewAdapter.java:116)
         at android.support.v7.widget.RecyclerView$Adapter.onBindViewHolder(RecyclerView.java:6673)
         at android.support.v7.widget.RecyclerView$Adapter.bindViewHolder(RecyclerView.java:6714)
         at android.support.v7.widget.RecyclerView$Recycler.tryBindViewHolderByDeadline(RecyclerView.java:5647)
         at android.support.v7.widget.RecyclerView$Recycler.tryGetViewHolderForPositionByDeadline(RecyclerView.java:5913)
         at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:5752)
         at android.support.v7.widget.RecyclerView$Recycler.getViewForPosition(RecyclerView.java:5748)
         at android.support.v7.widget.LinearLayoutManager$LayoutState.next(LinearLayoutManager.java:2232)
         at android.support.v7.widget.LinearLayoutManager.layoutChunk(LinearLayoutManager.java:1559)
         at android.support.v7.widget.LinearLayoutManager.fill(LinearLayoutManager.java:1519)
         at android.support.v7.widget.LinearLayoutManager.onLayoutChildren(LinearLayoutManager.java:614)
         at android.support.v7.widget.RecyclerView.dispatchLayoutStep2(RecyclerView.java:3812)
         at android.support.v7.widget.RecyclerView.dispatchLayout(RecyclerView.java:3529)
         at android.support.v7.widget.RecyclerView.consumePendingUpdateOperations(RecyclerView.java:1737)
         at android.support.v7.widget.RecyclerView.scrollByInternal(RecyclerView.java:1804)
         at android.support.v7.widget.RecyclerView.scrollBy(RecyclerView.java:1722)
         at com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter.onBindViewHolder(MainRecyclerViewAdapter.java:89)
         at com.work.hany.playinseoul.main.adapter.MainRecyclerViewAdapter.onBindViewHolder(MainRecyclerViewAdapter.java:26)
         at android.support.v7.widget.RecyclerView$Adapter.onBindViewHolder(RecyclerView.java:6673)
         at android.support.v7.widget.RecyclerView$Adapter.bindViewHolder(RecyclerView.java:6714)
         at android.support.v7.widget.RecyclerView$Recycler.tryBindViewHolderByDeadline(RecyclerView.java:5647)
         at android.support.v7.widget.RecyclerView$Recycler.tryGetViewHolderForPositionByDeadline(RecyclerView.java:5913)
         at android.support.v7.widget.GapWorker.prefetchPositionWithDeadline(GapWorker.java:285)
         at android.support.v7.widget.GapWorker.flushTaskWithDeadline(GapWorker.java:342)
         at android.support.v7.widget.GapWorker.flushTasksWithDeadline(GapWorker.java:358)
         at android.support.v7.widget.GapWorker.prefetch(GapWorker.java:365)
         at android.support.v7.widget.GapWorker.run(GapWorker.java:396)
         at android.os.Handler.handleCallback(Handler.java:751)
         at android.os.Handler.dispatchMessage(Handler.java:95)
         at android.os.Looper.loop(Looper.java:154)
         at android.app.ActivityThread.main(ActivityThread.java:6682)
         at java.lang.reflect.Method.invoke(Native Method)
         at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1520)
         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1410)

         * */

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
