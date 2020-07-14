package com.lazyfools.magusbuddy.page.codex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.page.common.onClickListener;

import java.util.List;

import static com.lazyfools.magusbuddy.utility.Utility.getSmallCapsString;

public abstract class CategoryListAdapter<Item,Listener extends onClickListener<Item>> extends RecyclerView.Adapter<CategoryListAdapter<Item,Listener>.ViewHolder> {
    protected final Context _context;
    protected List<Item> _items;
    protected Listener _listener;

    public CategoryListAdapter(Listener listener, Context context) {
        _listener = listener;
        _context = context;
    }

    @NonNull
    @Override
    public CategoryListAdapter<Item, Listener>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        return new CategoryListAdapter<Item,Listener>.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryListAdapter<Item,Listener>.ViewHolder holder, int position) {
        holder.item = _items.get(position);
        holder.contentView.setText(getSmallCapsString(getItemText(holder,position)));
        holder.imageView.setImageDrawable(_context.getResources().getDrawable(getItemDrawable(holder,position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != _listener) {
                    _listener.onClick((Item) holder.item);
                }
            }
        });
    }

    protected abstract String getItemText(final CategoryListAdapter<Item,Listener>.ViewHolder view, int position);
    protected abstract Integer getItemDrawable(final CategoryListAdapter<Item,Listener>.ViewHolder view, int position);

    @Override
    public int getItemCount() {
        if (_items == null){
            return 0;
        }
        return _items.size();
    }

    public void setItems(List<Item> items) {
        this._items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView contentView;
        public final ImageView imageView;
        public Item item;

        public ViewHolder(View view) {
            super(view);
            contentView = view.findViewById(R.id.text);
            imageView = view.findViewById(R.id.image);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
