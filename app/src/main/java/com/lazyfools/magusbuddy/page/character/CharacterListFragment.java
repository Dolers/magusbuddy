package com.lazyfools.magusbuddy.page.character;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazyfools.magusbuddy.viewmodel.DatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onListFragmentLongClickListener}
 * interface.
 */
public class CharacterListFragment extends Fragment {
    private onListFragmentLongClickListener _listener;
    private DatabaseViewModel _viewModel;
    private MyCharacterListRecyclerViewAdapter _adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterListFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        _listener = new onListFragmentLongClickListener(){
            @Override
            public void onListFragmentLongClick(CharacterEntity item) {
                //TODO define interaction
            }
        };

        _adapter = new MyCharacterListRecyclerViewAdapter(_listener);
        recyclerView.setAdapter(_adapter);

        return recyclerView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _listener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface onListFragmentLongClickListener {
        void onListFragmentLongClick(CharacterEntity item);
    }
}
