package com.lazyfools.magusbuddy.page.codex;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class CodexCategoryListAdapter extends CategoryListAdapter<CodexEntity, CodexCategoryListFragment.CodexCategoryOnClickListener> {
    static Integer matchImageToResource(CodexEntity.TypeEnum imageEnum)
    {
        switch (imageEnum){
            case BATTLESITUATION: return R.drawable.battlesituation_512;
            case BARDMAGIC: return R.drawable.bard_512;
            case WITCHMAGIC: return R.drawable.witch_512;
            case WARLOCKMAGIC: return R.drawable.warlock_512;
            case HIGHMAGIC: return R.drawable.high_512;
            case PSZIMAGIC: return R.drawable.pszi_512;
            case SACRALMAGIC: return R.drawable.sacral_512;
            case FIREMAGIC: return R.drawable.fire_512;
            default: return R.drawable.qualification_512;
        }
    }

    public CodexCategoryListAdapter(CodexCategoryListFragment.CodexCategoryOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position) {
        return ((CodexEntity)view.item).getName();
    }

    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        return matchImageToResource(((CodexEntity)view.item).getType());
    }
}
