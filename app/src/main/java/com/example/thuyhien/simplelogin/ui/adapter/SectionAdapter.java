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
import java.util.List;
import java.util.Map;

/**
 * Created by thuyhien on 10/11/17.
 */

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private List<Section> sectionList;
    private Map<Integer, List<MediaFeed>> totalMediaList;

    public SectionAdapter(List<Section> sectionList) {
        this.sectionList = sectionList;
        totalMediaList = new HashMap<>();
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        return new SectionViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {
        List<MediaFeed> mediaFeedList = totalMediaList.get(position);
        if (mediaFeedList == null) {
            mediaFeedList = new ArrayList<>();
        }
        holder.bindContentSection(sectionList.get(position), mediaFeedList);
    }

    @Override
    public int getItemCount() {
        return sectionList.size();
    }

    public void updateSection(Map<Section, List<MediaFeed>> mediaFeedList) {
        if (sectionList != null) {
            Section sectionKey = (Section) mediaFeedList.keySet().toArray()[0];
            int pos = sectionList.indexOf(sectionKey);
            if (pos != -1 && totalMediaList != null) {
                totalMediaList.put(pos, mediaFeedList.get(sectionKey));
                notifyItemChanged(pos);
            }
        }
    }
}
