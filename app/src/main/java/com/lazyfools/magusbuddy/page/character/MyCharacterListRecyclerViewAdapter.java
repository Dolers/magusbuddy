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

    private List<CharacterEntity> mItems;
    private final onListFragmentLongClickListener mListener;

    public MyCharacterListRecyclerViewAdapter(onListFragmentLongClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_characterlist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.mContentView.setText(mItems.get(position).getName());

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentLongClick(holder.mItem);
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

    public void setItems(List<CharacterEntity> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public CharacterEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
