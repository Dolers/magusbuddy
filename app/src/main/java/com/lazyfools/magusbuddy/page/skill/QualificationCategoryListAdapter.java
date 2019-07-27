package com.lazyfools.magusbuddy.page.skill;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QualificationCategoryListAdapter extends RecyclerView.Adapter<QualificationCategoryListAdapter.ViewHolder> {
    private final Context mContext;
    private List<QualificationType> mItems;
    private QualificationCategoryListFragment.onClickListener mListener;
    private final Map<QualificationEntity.QualificationTypeEnum,Integer> qualificationDrawableIds = new HashMap<QualificationEntity.QualificationTypeEnum, Integer>(){
        {
            put(QualificationEntity.QualificationTypeEnum.HARCI,R.drawable.kepzettseg_harci);
            put(QualificationEntity.QualificationTypeEnum.VILAGI, R.drawable.kepzettseg_vilagi);
            put(QualificationEntity.QualificationTypeEnum.SZOCIALIS, R.drawable.kepzettseg_szocialis);
            put(QualificationEntity.QualificationTypeEnum.ALVILAGI, R.drawable.kepzettseg_alvilagi);
            put(QualificationEntity.QualificationTypeEnum.TUDOMANYOS, R.drawable.kepzettseg_tudomanyos);
            put(QualificationEntity.QualificationTypeEnum.MISZTIKUS, R.drawable.kepzettseg_misztikus);
        }
    };

    public QualificationCategoryListAdapter(QualificationCategoryListFragment.onClickListener listener, Context context) {
        mListener = listener;
        mContext = context;
    }

    @Override
    public QualificationCategoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skills_category_item, parent, false);
        return new QualificationCategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QualificationCategoryListAdapter.ViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.mContentView.setText(mItems.get(position).getType().toString());
        holder.mImageView.setImageDrawable(getDrawable(holder.mItem.type));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClick(holder.mItem);
                }
            }
        });
    }

    private Drawable getDrawable(QualificationEntity.QualificationTypeEnum type) {
        return mContext.getResources().getDrawable(qualificationDrawableIds.get(type));
    }

    @Override
    public int getItemCount() {
        if (mItems == null){
            return 0;
        }
        return mItems.size();
    }

    public void setItems(List<QualificationType> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final ImageView mImageView;
        public QualificationType mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.text);
            mImageView = view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
