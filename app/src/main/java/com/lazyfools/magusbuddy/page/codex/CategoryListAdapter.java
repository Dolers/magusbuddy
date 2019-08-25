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

import java.util.List;

import static com.lazyfools.magusbuddy.utility.Utility.getSmallCapsString;

public abstract class CategoryListAdapter<Item,Listener extends onClickListener<Item>> extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {
    protected final Context _context;
    protected List<Item> _items;
    protected Listener _listener;

    public CategoryListAdapter(Listener listener, Context context) {
        _listener = listener;
        _context = context;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryListAdapter.ViewHolder holder, int position) {
        holder.item = _items.get(position);
        holder.contentView.setText(getSmallCapsString(getItemText(holder,position)));
        holder.imageView.setImageDrawable(_context.getResources().getDrawable(getItemDrawable(holder,position)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != _listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    _listener.onClick((Item) holder.item);
                }
            }
        });
    }

    protected abstract String getItemText(final CategoryListAdapter.ViewHolder view, int position);
    protected abstract Integer getItemDrawable(final CategoryListAdapter.ViewHolder view, int position);
        //(qualificationDrawableIds.get(holder.item.type)));


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
