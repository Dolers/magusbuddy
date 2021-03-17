package com.lazyfools.magusbuddy.page.battle;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.utility.BasicCallback;
import com.lazyfools.magusbuddy.utility.CustomNumberPicker;

import co.paulburke.android.itemtouchhelper.helper.ItemTouchHelperAdapter;
import co.paulburke.android.itemtouchhelper.helper.ItemTouchHelperViewHolder;
import co.paulburke.android.itemtouchhelper.helper.OnStartDragListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link BattleItem} and makes a call to the
 * specified {@link OnStartDragListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class BattleListAdapter extends RecyclerView.Adapter<BattleListAdapter.ItemViewHolder>
            implements ItemTouchHelperAdapter {

    private final BasicCallback _updateHighlight;
    private List<BattleItem> _items;

    private final OnStartDragListener _dragStartListener;

    public BattleListAdapter(OnStartDragListener dragStartListener, BasicCallback updateHighlight) {
        _dragStartListener = dragStartListener;
        _updateHighlight = updateHighlight;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_battle_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder._item = _items.get(position);
        holder._tvCharacterName.setText(_items.get(position).getCharacter().getName());

        holder._numberPicker.setOnValueChangedListener(new CustomNumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(int newValue) {
                holder._item.setCurrentSegment(newValue);
                _updateHighlight.callback();
            }
        });
    }


    @Override
    public void onItemDismiss(int position) {
        _items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(_items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        if (_items == null){
            return 0;
        }
        return _items.size();
    }

    public void setItems(List<CharacterEntity> items) {
        _items = new ArrayList<>();
        for (CharacterEntity item : items){
            _items.add(new BattleItem(item,0));
        }
        notifyDataSetChanged();
    }
    /*
    public void addItem(BattleItem item){
        _items.add(item);
        notifyItemInserted(_items.size() - 1);
    }
*/
    /**
     * Simple example of a view holder that implements {@link ItemTouchHelperViewHolder} and has a
     * "handle" view that initiates a drag event when touched.
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final View _view;
        public final TextView _tvCharacterName;
        public CustomNumberPicker _numberPicker;
        public BattleItem _item;

        public ItemViewHolder(View itemView) {
            super(itemView);
            _view = itemView;
            _tvCharacterName = itemView.findViewById(R.id.content);
            _numberPicker = itemView.findViewById(R.id.initiation_number_picker);

        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.BLUE);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
