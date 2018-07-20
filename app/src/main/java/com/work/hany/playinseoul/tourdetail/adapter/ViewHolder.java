package com.work.hany.playinseoul.tourdetail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.work.hany.playinseoul.model.Section;

import java.util.ArrayList;

public abstract class ViewHolder<T> extends  RecyclerView.ViewHolder {
    public ViewHolder(View itemView) {
        super(itemView);
    }
    abstract public void bind(T data);

}