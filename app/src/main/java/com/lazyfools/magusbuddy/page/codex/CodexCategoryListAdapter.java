package com.lazyfools.magusbuddy.page.codex;

import android.content.Context;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;

public class CodexCategoryListAdapter extends CategoryListAdapter<CodexEntity, CodexCategoryListFragment.CodexCategoryOnClickListener> {
    public CodexCategoryListAdapter(CodexCategoryListFragment.CodexCategoryOnClickListener listener, Context context) {
        super(listener, context);
    }

    @Override
    protected String getItemText(CategoryListAdapter.ViewHolder view, int position) {
        return ((CodexEntity)view.item).getName();
    }

    @Override
    protected Integer getItemDrawable(CategoryListAdapter.ViewHolder view, int position) {
        return R.drawable.kepzettseg_misztikus;
    }
}
