package com.lazyfools.magusbuddy.page.codex.highmagic;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicType;
import com.lazyfools.magusbuddy.page.codex.CategoryListAdapter;

import java.util.HashMap;
import java.util.Map;

public class HighMagicCategoryListAdapter extends CategoryListAdapter<HighMagicType, HighMagicCategoryListFragment.HighMagicTypeOnClickListener> {
    private final Map<HighMagicEntity.TypeEnum,Integer> HighMagicDrawableIds = new HashMap<HighMagicEntity.TypeEnum, Integer>(){
        {
            put(HighMagicEntity.TypeEnum.ELEMI,       R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.TERMÉSZETI,  R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.TER,         R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.ASZTRAL,     R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.MENTAL,      R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.RUNA,        R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.IDO,         R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.NEKROMANCIA, R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.DEMONOLOGIA, R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.SZIMPATIKUS, R.drawable.kepzettseg_harci);
            put(HighMagicEntity.TypeEnum.EGYEB,       R.drawable.kepzettseg_harci);

        }
    };

    public HighMagicCategoryListAdapter(HighMagicCategoryListFragment.HighMagicTypeOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position){
        return ((HighMagicType)view.item).getType().toString();
    }

    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        HighMagicEntity.TypeEnum typeEnum = ((HighMagicType)view.item).getType();
        return HighMagicDrawableIds.get(typeEnum);
    }
}
