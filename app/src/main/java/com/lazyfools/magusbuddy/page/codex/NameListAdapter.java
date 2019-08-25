package com.lazyfools.magusbuddy.page.codex;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

public class NameListAdapter extends RecyclerView.Adapter<NameListAdapter.ViewHolder> {
    private List<NameEntity> _items;
    private onClickListener _listener;

    public NameListAdapter( onClickListener listener) {
        _listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.name_list_item, parent, false);
        return new NameListAdapter.ViewHolder(view);
}

    @Override
    public void onBindViewHolder(@NonNull final NameListAdapter.ViewHolder holder, int position) {
        holder.item = _items.get(position);
        holder.contentView.setText(_items.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != _listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    _listener.onClick(holder.item);
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

    public void setItems(List<NameEntity> items) {
        this._items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView contentView;
        public NameEntity item;

        public ViewHolder(View view) {
            super(view);
            contentView = view.findViewById(R.id.content);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
