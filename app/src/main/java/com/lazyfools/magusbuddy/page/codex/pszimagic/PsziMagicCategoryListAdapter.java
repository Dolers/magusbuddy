package com.lazyfools.magusbuddy.page.codex.pszimagic;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicType;
import com.lazyfools.magusbuddy.page.codex.CategoryListAdapter;

import java.util.HashMap;
import java.util.Map;

public class PsziMagicCategoryListAdapter extends CategoryListAdapter<PsziMagicType, PsziMagicCategoryListFragment.PsziMagicTypeOnClickListener> {
    private final Map<PsziMagicEntity.TypeEnum,Integer> PsziMagicDrawableIds = new HashMap<PsziMagicEntity.TypeEnum, Integer>(){
        {
            put(PsziMagicEntity.TypeEnum.GODONI,     R.drawable.kepzettseg_harci);
            put(PsziMagicEntity.TypeEnum.KYR,     R.drawable.kepzettseg_harci);
            put(PsziMagicEntity.TypeEnum.PYARRONI,    R.drawable.kepzettseg_harci);
            put(PsziMagicEntity.TypeEnum.SLAN,    R.drawable.kepzettseg_harci);

        }
    };

    public PsziMagicCategoryListAdapter(PsziMagicCategoryListFragment.PsziMagicTypeOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position) {
        return ((PsziMagicType)view.item).getType().toString();
    }


    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        PsziMagicEntity.TypeEnum typeEnum = ((PsziMagicType)view.item).getType();
        return PsziMagicDrawableIds.get(typeEnum);
    }
}
