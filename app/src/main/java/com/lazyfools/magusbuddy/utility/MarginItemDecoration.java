package com.lazyfools.magusbuddy.utility;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Type;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {
    private enum TypeEnum {ONE_VALUED,FOUR_VALUED}
    private TypeEnum _type;
    private Integer _spaceHeight = null;
    private Integer _topHeight = null;
    private Integer _bottomHeight = null;
    private Integer _leftHeight = null;
    private Integer _rightHeight = null;

    public MarginItemDecoration(Integer spaceHeight){
        _spaceHeight = spaceHeight;
        _type = TypeEnum.ONE_VALUED;
    }

    public MarginItemDecoration(Integer topHeight, Integer bottomHeight, Integer leftHeight,Integer rightHeight){
        _topHeight = topHeight;
        _bottomHeight = bottomHeight;
        _leftHeight = leftHeight;
        _rightHeight = rightHeight;
        _type = TypeEnum.FOUR_VALUED;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (_type == TypeEnum.ONE_VALUED) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = _spaceHeight;
            }
            outRect.left = _spaceHeight;
            outRect.right = _spaceHeight;
            outRect.bottom = _spaceHeight;
        }
        else
        {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = _topHeight;
            }
            outRect.left = _leftHeight;
            outRect.right = _rightHeight;
            outRect.bottom = _bottomHeight;
        }
    }
}