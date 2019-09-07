package com.lazyfools.magusbuddy.page.codex.bardmagic;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.database.entity.BardMagicType;
import com.lazyfools.magusbuddy.page.codex.CategoryListAdapter;

import java.util.HashMap;
import java.util.Map;

public class BardMagicCategoryListAdapter extends CategoryListAdapter<BardMagicType, BardMagicCategoryListFragment.BardMagicTypeOnClickListener> {
    private final Map<BardMagicEntity.TypeEnum,Integer> BardMagicDrawableIds = new HashMap<BardMagicEntity.TypeEnum, Integer>(){
        {
            put(BardMagicEntity.TypeEnum.DAL,   R.drawable.kepzettseg_harci);
            put(BardMagicEntity.TypeEnum.FENY,  R.drawable.kepzettseg_harci);
            put(BardMagicEntity.TypeEnum.HANG,  R.drawable.kepzettseg_harci);
            put(BardMagicEntity.TypeEnum.EGYEB, R.drawable.kepzettseg_harci);

        }
    };

    public BardMagicCategoryListAdapter(BardMagicCategoryListFragment.BardMagicTypeOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position){
        return ((BardMagicType)view.item).getType().toString();
    }

    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        BardMagicEntity.TypeEnum typeEnum = ((BardMagicType)view.item).getType();
        return BardMagicDrawableIds.get(typeEnum);
    }
}
