package com.lazyfools.magusbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import ir.mirrajabi.searchdialog.core.Searchable;

public class SearchAdapter extends BaseAdapter {
    private final SearchActivity.onClickListener mListener;
    private List<Searchable> mItems;
    private final int mLayoutResource;

    public SearchAdapter(int layoutResource, SearchActivity.onClickListener listener) {
        mLayoutResource = layoutResource;
        mListener = listener;
    }

    @Override
    public int getCount() {
        if (mItems != null)
            return mItems.size();

        return 0;
    }

    @Override
    public Object getItem(int position) { return mItems.get(position); }

    public void setItems(List<? extends Searchable> items) {
        this.mItems = (List<Searchable>) items;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(mLayoutResource, parent, false);
            holder = new ViewHolder(convertView);
            holder.mContentView = convertView.findViewById(R.id.content);
            holder.mItem = mItems.get(position);
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
            
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.mContentView.setText(holder.mItem.getTitle());
        return convertView;

    }

    static class ViewHolder{
        View mView;
        public Searchable mItem;
        public TextView mContentView;
        ViewHolder(View view){
            mView = view;
            mContentView = view.findViewById(R.id.content);
        }

    }

}
