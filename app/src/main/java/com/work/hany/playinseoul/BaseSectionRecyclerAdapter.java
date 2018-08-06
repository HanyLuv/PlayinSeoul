package com.work.hany.playinseoul;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.tourdetail.adapter.ViewHolder;

import java.util.ArrayList;

import static com.work.hany.playinseoul.model.Section.ItemType.NOTHING;

abstract public class BaseSectionRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    protected ArrayList<Section> sections;

    @Override
    public int getItemCount() {
        return sections.size();
    }

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
    public int getItemViewType(int position) {
        return sections.get(position).getType().getCode();
    }

    protected Section.ItemType getCurrentItemType(int position) {
        for (Section.ItemType itemType : Section.ItemType.values()) {
            if (itemType.getCode() == position) return itemType;
        }

        return NOTHING;
    }

}
