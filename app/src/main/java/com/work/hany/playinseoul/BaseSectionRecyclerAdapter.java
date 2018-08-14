package com.work.hany.playinseoul;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.work.hany.playinseoul.model.Section;
import com.work.hany.playinseoul.tourdetail.adapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.Iterator;

import static com.work.hany.playinseoul.model.Section.ItemType.NOTHING;

abstract public class BaseSectionRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    protected ArrayList<Section> sections;

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public <T>void addSection(Section.ItemType type, T data){
        sections.add(new Section(type,data));
        notifyDataSetChanged();
    }

    public <T> void updateSection(Section.ItemType type, T data) {
        for(Iterator<Section> i = sections.iterator(); i.hasNext();){
            Section section = i.next();
            if(section.getType().equals(type)) {
                section.setData(data);
                notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        if (holder.getAdapterPosition() != RecyclerView.NO_POSITION ) {
            position = holder.getAdapterPosition();
        }

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
