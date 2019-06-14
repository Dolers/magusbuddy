package com.lazyfools.magusbuddy.character;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.CharacterEntity;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onListFragmentLongClickListener}
 * interface.
 */
public class CharacterListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private onListFragmentLongClickListener mListener;
    private DatabaseViewModel mViewModel;

    public void setViewModel(DatabaseViewModel viewModel) { this.mViewModel = viewModel;}

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterListFragment() {
    }

    // TODO: Customize parameter initialization
    public static CharacterListFragment newInstance(DatabaseViewModel ViewModel) {
        CharacterListFragment fragment = new CharacterListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 1);
        fragment.setArguments(args);
        fragment.setViewModel(ViewModel);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("","onCreate");
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_characterlist_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            mListener = new onListFragmentLongClickListener(){
                @Override
                public void onListFragmentLongClick(CharacterEntity item) {
                    //TODO define interaction
                }
            };

            final MyCharacterListRecyclerViewAdapter adapter = new MyCharacterListRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(adapter);

            mViewModel.getAllCharacter().observe(this, new Observer<List<CharacterEntity>>() {
                @Override
                public void onChanged(@Nullable final List<CharacterEntity> characters) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setItems(characters);
                }
            });
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
        // TODO: Update argument type and name
        void onListFragmentLongClick(CharacterEntity item);
    }
}
