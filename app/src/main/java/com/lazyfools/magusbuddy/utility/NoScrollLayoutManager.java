package com.lazyfools.magusbuddy.utility;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class NoScrollLayoutManager extends LinearLayoutManager {
    public NoScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);

    }

    // it will always pass false to RecyclerView when calling "canScrollVertically()" method.
    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
