package com.lazyfools.magusbuddy.page.battle;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.utility.BasicCallback;

import java.util.List;

import co.paulburke.android.itemtouchhelper.helper.OnStartDragListener;
import co.paulburke.android.itemtouchhelper.helper.SimpleItemTouchHelperCallback;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnStartDragListener}
 * interface.
 */
public class BattleFragment extends Fragment implements OnStartDragListener {
    private ItemTouchHelper _itemTouchHelper;
    private DatabaseViewModel _viewModel;
    private MyBattleRecyclerViewAdapter _adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BattleFragment() {
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);
        _viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        _viewModel.getAllCharacters().observe(this, new Observer<List<CharacterEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CharacterEntity> characters) {
                // Update the cached copy of the words in the adapter.
                _adapter.setItems(characters);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("", "onCreateView: ");
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the adapter
        if (view instanceof RecyclerView) {
            final RecyclerView recyclerView = (RecyclerView) view;

            _adapter = new MyBattleRecyclerViewAdapter(this,
                    new BasicCallback(){
                        @Override
                        public void callback() {updateNextCharacterHighlight(recyclerView);}
                    });

            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(_adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(_adapter,false);
            _itemTouchHelper = new ItemTouchHelper(callback);
            _itemTouchHelper.attachToRecyclerView(recyclerView);
            Log.i("", "onViewCreated: ");
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        _itemTouchHelper.startDrag(viewHolder);
    }

    public void updateNextCharacterHighlight(RecyclerView recyclerView){
        int max = recyclerView.getAdapter().getItemCount();
        int minimalSegment = 10;
        int nextHighLightedCharacter = 0;
        for (int i = 0; i<max; i++) {
            MyBattleRecyclerViewAdapter.ItemViewHolder holder = (MyBattleRecyclerViewAdapter.ItemViewHolder) recyclerView.findViewHolderForLayoutPosition(i);
            if (holder._item.getCurrentSegment() < minimalSegment){
                minimalSegment = holder._item.getCurrentSegment();
                nextHighLightedCharacter = i;
            }
            holder._contentView.setTextColor(Color.BLACK);
        }

        if (minimalSegment != 10) {
            ((MyBattleRecyclerViewAdapter.ItemViewHolder) recyclerView.findViewHolderForLayoutPosition(nextHighLightedCharacter))._contentView.setTextColor(Color.RED);
            recyclerView.refreshDrawableState();
        }

    }
}
