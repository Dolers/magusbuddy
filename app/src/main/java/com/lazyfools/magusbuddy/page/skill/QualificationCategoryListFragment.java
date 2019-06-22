package com.lazyfools.magusbuddy.page.skill;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onClickListener}
 * interface.
 */
public class QualificationCategoryListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private onClickListener mListener;
    private DatabaseViewModel mViewModel;

    public void setViewModel(DatabaseViewModel viewModel) { this.mViewModel = viewModel;}

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QualificationCategoryListFragment() {
    }

    // TODO: Customize parameter initialization
    public static QualificationCategoryListFragment newInstance(DatabaseViewModel ViewModel) {
        QualificationCategoryListFragment fragment = new QualificationCategoryListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, 2);
        fragment.setArguments(args);
        fragment.setViewModel(ViewModel);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),getArguments().getInt(ARG_COLUMN_COUNT)));

        mListener = new onClickListener(){
            @Override
            public void onClick(QualificationType item) {
                //TODO create a fragment with only category items
            }
        };

        final QualificationListRecyclerViewAdapter adapter = new QualificationListRecyclerViewAdapter(mListener);
        recyclerView.setAdapter(adapter);

        mViewModel.getAllQualificationTypes().observe(this, new Observer<List<QualificationType>>() {
            @Override
            public void onChanged(@Nullable final List<QualificationType> qualifications) {
                // Update the cached copy of the words in the adapter.
                adapter.setItems(qualifications);
            }
        });
        return recyclerView;
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
    public interface onClickListener {
        void onClick(QualificationType item);
    }
}
