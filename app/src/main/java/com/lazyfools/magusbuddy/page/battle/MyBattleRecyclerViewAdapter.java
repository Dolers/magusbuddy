package com.lazyfools.magusbuddy.page.battle;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.utility.BasicCallback;

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
public class MyBattleRecyclerViewAdapter extends RecyclerView.Adapter<MyBattleRecyclerViewAdapter.ItemViewHolder>
            implements ItemTouchHelperAdapter {

    private final BasicCallback mUpdateHighlight;
    private List<BattleItem> mItems;

    private final OnStartDragListener mDragStartListener;
    private boolean mASeekBarIsActive = false;

    public MyBattleRecyclerViewAdapter(OnStartDragListener dragStartListener, BasicCallback updateHighlight) {
        mDragStartListener = dragStartListener;
        mUpdateHighlight = updateHighlight;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_battle_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.mItem = mItems.get(position);
        holder.mContentView.setText(mItems.get(position).getCharacter().getName());

        // Start a drag whenever the handle view it touched
        holder.mContentView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("", "onTouch: ");
                //TODO it should be long touch
                if (!mASeekBarIsActive) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
        holder.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                holder.mItem.setCurrentSegment(seekBar.getProgress());
                mUpdateHighlight.callback();
                mASeekBarIsActive = false;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mASeekBarIsActive = true;
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {}
        });
    }


    @Override
    public void onItemDismiss(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mItems, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        if (mItems == null){
            return 0;
        }
        return mItems.size();
    }

    public void setItems(List<CharacterEntity> items) {
        mItems = new ArrayList<>();
        for (CharacterEntity item : items){
            mItems.add(new BattleItem(item,0));
        }
        notifyDataSetChanged();
    }
    /*
    public void addItem(BattleItem item){
        mItems.add(item);
        notifyItemInserted(mItems.size() - 1);
    }
*/
    /**
     * Simple example of a view holder that implements {@link ItemTouchHelperViewHolder} and has a
     * "handle" view that initiates a drag event when touched.
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {

        public final View mView;
        public final TextView mContentView;
        public SeekBar mSeekBar;
        public BattleItem mItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mContentView = itemView.findViewById(R.id.content);
           mSeekBar = itemView.findViewById(R.id.seekBar);

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
