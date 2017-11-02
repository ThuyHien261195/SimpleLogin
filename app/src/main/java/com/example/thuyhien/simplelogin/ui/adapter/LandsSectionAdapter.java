package com.example.thuyhien.simplelogin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thuyhien.simplelogin.R;
import com.example.thuyhien.simplelogin.model.Section;
import com.example.thuyhien.simplelogin.ui.viewholder.LandsSectionViewHolder;

import java.util.List;

/**
 * Created by thuyhien on 10/27/17.
 */

public class LandsSectionAdapter extends SectionAdapter {
    public LandsSectionAdapter(List<Section> sectionList, String langCode) {
        super(sectionList, langCode);
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_section, parent, false);
        return new LandsSectionViewHolder(view, langCode);
    }
}
