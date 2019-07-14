package com.lazyfools.magusbuddy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lazyfools.magusbuddy.database.entity.QualificationName;

import java.util.List;

class SkillSearchAdapter  extends BaseAdapter {


    private List<QualificationName> mItems;

    public SkillSearchAdapter() { }

    @Override
    public int getCount() {
        if (mItems != null)
            return mItems.size();

        return 0;
    }

    @Override
    public Object getItem(int position) { return mItems.get(position); }

    public void setItems(List<QualificationName> mItems) {
        this.mItems = mItems;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.skills_search_listitem, parent, false);
            listViewHolder.mContent = convertView.findViewById(R.id.content);
            convertView.setTag(listViewHolder);
        }
        else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }

        listViewHolder.mContent.setText(mItems.get(position).getName());
        return convertView;

    }

    static class ViewHolder{
        TextView mContent;
    }

}
