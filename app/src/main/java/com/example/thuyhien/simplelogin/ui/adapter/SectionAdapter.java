package com.example.thuyhien.simplelogin.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.MediaFeed;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.ui.viewholder.SectionViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thuyhien on 10/11/17.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private LinkedHashMap<Section, List<MediaFeed>> totalMediaList;

    public SectionAdapter(List<Section> sectionList) {
        totalMediaList = new LinkedHashMap<>();
        for (Section section : sectionList) {
            totalMediaList.put(section, new ArrayList<MediaFeed>());
        }
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        return new SectionViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        Section section = new ArrayList<>(totalMediaList.keySet()).get(position);
        List<MediaFeed> mediaFeedList = totalMediaList.get(section);
        if (mediaFeedList == null) {
            mediaFeedList = new ArrayList<>();
        }
        holder.bindContentSection(section, mediaFeedList);
    }

    @Override
    public int getItemCount() {
        return totalMediaList.size();
    }

    public void updateSection(Section section, List<MediaFeed> mediaFeedList) {
        if (totalMediaList != null) {
            List<Section> sectionList = new ArrayList<>(totalMediaList.keySet());
            int pos = sectionList.indexOf(section);
            if (pos != -1) {
                totalMediaList.put(section, mediaFeedList);
                notifyItemChanged(pos);
            }
        }
    }
}
