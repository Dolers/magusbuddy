package com.lazyfools.magusbuddy.page.skill;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationName;

import java.util.List;

class QualificationListAdapter extends RecyclerView.Adapter<QualificationListAdapter.ViewHolder> {
    private List<QualificationName> _items;
    private QualificationListFragment.onClickListener _listener;

    public QualificationListAdapter(QualificationListFragment.onClickListener listener) {
        _listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.fragment_skills_item, parent, false);
        return new QualificationListAdapter.ViewHolder(view);
}

    @Override
    public void onBindViewHolder(final QualificationListAdapter.ViewHolder holder, int position) {
        holder._item = _items.get(position);
        holder._contentView.setText(_items.get(position).getName());

        holder._view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != _listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    _listener.onClick(holder._item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (_items == null){
            return 0;
        }
        return _items.size();
    }

    public void setItems(List<QualificationName> items) {
        this._items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View _view;
        public final TextView _contentView;
        public QualificationName _item;

        public ViewHolder(View view) {
            super(view);
            _view = view;
            _contentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + _contentView.getText() + "'";
        }
    }
}
