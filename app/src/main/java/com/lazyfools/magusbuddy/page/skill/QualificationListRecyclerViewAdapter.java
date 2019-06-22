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

public class QualificationListRecyclerViewAdapter extends RecyclerView.Adapter<QualificationListRecyclerViewAdapter.ViewHolder> {
    private List<QualificationType> mItems;
    private QualificationCategoryListFragment.onClickListener mListener;

    public QualificationListRecyclerViewAdapter(QualificationCategoryListFragment.onClickListener listener) {
        mListener = listener;
    }

    @Override
    public QualificationListRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skills_item, parent, false);
        return new QualificationListRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QualificationListRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.mContentView.setText(mItems.get(position).getType().toString());

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onClick(holder.mItem);
                    return true;
                }
                return false;
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
