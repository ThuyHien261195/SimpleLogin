package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.ui.viewholder.SectionViewHolder;

import java.util.List;

/**
 * Created by thuyhien on 10/11/17.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private List<Section> sectionList;

    public SectionAdapter(List<Section> sectionList) {
        this.sectionList = sectionList;
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        return new SectionViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        holder.bindContentSection(sectionList.get(position));
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }
}
