package com.lazyfools.magusbuddy.page.skill;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

public class QualificationCategoryListAdapter extends RecyclerView.Adapter<QualificationCategoryListAdapter.ViewHolder> {
    private List<QualificationType> mItems;
    private QualificationCategoryListFragment.onClickListener mListener;

    public QualificationCategoryListAdapter(QualificationCategoryListFragment.onClickListener listener) {
        mListener = listener;
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
            mContentView = (TextView) view.findViewById(R.id.text);
            mImageView = (ImageView) view.findViewById(R.id.image);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
