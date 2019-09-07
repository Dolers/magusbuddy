package com.lazyfools.magusbuddy.page.codex.sacralmagic;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicType;
import com.lazyfools.magusbuddy.page.codex.CategoryListAdapter;

import java.util.HashMap;
import java.util.Map;

public class SacralMagicCategoryListAdapter extends CategoryListAdapter<SacralMagicType, SacralMagicCategoryListFragment.SacralMagicTypeOnClickListener> {
    private final Map<SacralMagicEntity.TypeEnum,Integer> SacralMagicDrawableIds = new HashMap<SacralMagicEntity.TypeEnum, Integer>(){
        {
            put(SacralMagicEntity.TypeEnum.NAGY,    R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.KIS,     R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.AREL,    R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.DARTON,  R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.DOMVIK,  R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.DREINA,  R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.KRAD,    R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.RANAGOL, R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.SOGRON,  R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.THARR,   R.drawable.kepzettseg_harci);
            put(SacralMagicEntity.TypeEnum.UWEL,    R.drawable.kepzettseg_harci);

        }
    };

    public SacralMagicCategoryListAdapter(SacralMagicCategoryListFragment.SacralMagicTypeOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position) {
        return ((SacralMagicType)view.item).getType().toString();
    }


    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        SacralMagicEntity.TypeEnum typeEnum = ((SacralMagicType)view.item).getType();
        return SacralMagicDrawableIds.get(typeEnum);
    }
}
