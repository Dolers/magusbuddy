package com.lazyfools.magusbuddy.page.codex.firemagic;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;
import com.lazyfools.magusbuddy.database.entity.FireMagicType;
import com.lazyfools.magusbuddy.page.codex.CategoryListAdapter;

import java.util.HashMap;
import java.util.Map;

public class FireMagicCategoryListAdapter extends CategoryListAdapter<FireMagicType, FireMagicCategoryListFragment.FireMagicTypeOnClickListener> {
    private final Map<FireMagicEntity.TypeEnum,Integer> FireMagicDrawableIds = new HashMap<FireMagicEntity.TypeEnum, Integer>(){
        {
            put(FireMagicEntity.TypeEnum.ALAP,        R.drawable.kepzettseg_harci);
            put(FireMagicEntity.TypeEnum.VEDO,        R.drawable.kepzettseg_harci);
            put(FireMagicEntity.TypeEnum.ISKOLA,      R.drawable.kepzettseg_harci);
            put(FireMagicEntity.TypeEnum.SZABAD,      R.drawable.kepzettseg_harci);
            put(FireMagicEntity.TypeEnum.MAGASISKOLA, R.drawable.kepzettseg_harci);
            put(FireMagicEntity.TypeEnum.KOZONSEGES,  R.drawable.kepzettseg_harci);
            put(FireMagicEntity.TypeEnum.OSTUZ,       R.drawable.kepzettseg_harci);
            put(FireMagicEntity.TypeEnum.TUZLENY,     R.drawable.kepzettseg_harci);
        }
    };

    public FireMagicCategoryListAdapter(FireMagicCategoryListFragment.FireMagicTypeOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position) {
        return ((FireMagicType)view.item).getType().toString();
    }

    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        FireMagicEntity.TypeEnum typeEnum = ((FireMagicType)view.item).getType();
        return FireMagicDrawableIds.get(typeEnum);
    }
}
