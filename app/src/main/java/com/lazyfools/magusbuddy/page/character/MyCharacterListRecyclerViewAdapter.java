package com.lazyfools.magusbuddy.page.character;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.page.character.CharacterListFragment.onListFragmentLongClickListener;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a (@link CharacterItem) and makes a call to the
 * specified {@link onListFragmentLongClickListener}.
 */
public class MyCharacterListRecyclerViewAdapter extends RecyclerView.Adapter<MyCharacterListRecyclerViewAdapter.ViewHolder> {

    private List<CharacterEntity> _items;
    private final onListFragmentLongClickListener _listener;

    public MyCharacterListRecyclerViewAdapter(onListFragmentLongClickListener listener) {
        _listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_characterlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder._item = _items.get(position);
        holder._contentView.setText(_items.get(position).getName());

        holder._view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != _listener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    _listener.onListFragmentLongClick(holder._item);
                    return true;
                }
                return false;
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

    public void setItems(List<CharacterEntity> items) {
        this._items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View _view;
        public final TextView _contentView;
        public CharacterEntity _item;

        public ViewHolder(View view) {
            super(view);
            _view = view;
            _contentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + _contentView.getText() + "'";
        }
    }
}
