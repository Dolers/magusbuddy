package com.lazyfools.magusbuddy.page.codex.qualification;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;
import com.lazyfools.magusbuddy.page.codex.CategoryListAdapter;

import java.util.HashMap;
import java.util.Map;

public class QualificationCategoryListAdapter extends CategoryListAdapter<QualificationType, QualificationCategoryListFragment.qualificationTypeOnClickListener> {
    private final Map<QualificationEntity.TypeEnum,Integer> qualificationDrawableIds = new HashMap<QualificationEntity.TypeEnum, Integer>(){
        {
            put(QualificationEntity.TypeEnum.HARCI,      R.drawable.kepzettseg_harci);
            put(QualificationEntity.TypeEnum.VILAGI,     R.drawable.kepzettseg_vilagi);
            put(QualificationEntity.TypeEnum.SZOCIALIS,  R.drawable.kepzettseg_szocialis);
            put(QualificationEntity.TypeEnum.ALVILAGI,   R.drawable.kepzettseg_alvilagi);
            put(QualificationEntity.TypeEnum.TUDOMANYOS, R.drawable.kepzettseg_tudomanyos);
            put(QualificationEntity.TypeEnum.MISZTIKUS,  R.drawable.kepzettseg_misztikus);
        }
    };

    public QualificationCategoryListAdapter(QualificationCategoryListFragment.qualificationTypeOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position) {
        return ((QualificationType)view.item).getType().toString();
    }

    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        QualificationEntity.TypeEnum typeEnum = ((QualificationType)view.item).getType();
        return qualificationDrawableIds.get(typeEnum);
    }
}
