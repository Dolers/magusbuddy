package com.lazyfools.magusbuddy.page.skill;


import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationName;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.ArrayList;
import java.util.List;

import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onClickListener}
 * interface.
 */
public class QualificationCategoryListFragment extends Fragment {

    // TODO: Customize parameters
    private onClickListener mListener;
    private DatabaseViewModel mViewModel;
    private QualificationCategoryListAdapter mAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public QualificationCategoryListFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        mViewModel.getAllQualificationTypes().observe(this, new Observer<List<QualificationType>>() {
            @Override
            public void onChanged(@Nullable final List<QualificationType> qualifications) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setItems(qualifications);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        Intent intent = getActivity().getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO do smth with query
        }
        mListener = new onClickListener(){
            @Override
            public void onClick(QualificationType item) {
                Navigation.findNavController(recyclerView).navigate(R.id.action_qualificationCategoryListFragment_to_qualificationListFragment);
            }
        };
        mAdapter = new QualificationCategoryListAdapter(mListener,container.getContext());
        recyclerView.setAdapter(mAdapter);

        return recyclerView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_icon:
                getActivity().onSearchRequested();
                /*
                List<QualificationName> names = mViewModel.getAllQualificationNames().getValue();

                SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat<QualificationName>(getActivity(), "Search...",
                        "What are you looking for...?", null, (ArrayList) names,
                        new SearchResultListener<QualificationName>() {
                            @Override
                            public void onSelected(
                                    BaseSearchDialogCompat dialog,
                                    QualificationName item, int position
                            ) {

                                dialog.dismiss();
                            }
                        }
                );
                dialog.show();
                dialog.getSearchBox().setTypeface(Typeface.SERIF);*/
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
