package com.lazyfools.magusbuddy.page.skill;


import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

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
    private RecyclerView mRecyclerView;

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
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

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

        mRecyclerView = new RecyclerView(container.getContext());
        mRecyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        Intent intent = getActivity().getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO do smth with query
        }
        mListener = new onClickListener(){
            @Override
            public void onClick(QualificationType item) {
                Bundle bundle = new Bundle();
                bundle.putString(getResources().getString(R.string.QUALIFICATION_TYPENAME), item.getType().toString());
                Navigation.findNavController(mRecyclerView).navigate(R.id.action_qualificationCategoryListFragment_to_qualificationListFragment,bundle);
            }
        };
        mAdapter = new QualificationCategoryListAdapter(mListener,container.getContext());
        mRecyclerView.setAdapter(mAdapter);

        return mRecyclerView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        //super.onCreateOptionsMenu(menu, inflater);
        final SearchView sv = new SearchView(( (HomeActivity)getActivity()).getSupportActionBar().getThemedContext());
        initSearchView(menu, sv);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("search query submit" + query);
                sv.clearFocus();
                Bundle bundle = new Bundle();
                bundle.putString(getResources().getString(R.string.QUALIFICATION_FILTER), query);
                Navigation.findNavController(mRecyclerView).navigate(R.id.action_qualificationCategoryListFragment_to_qualificationListFragment,bundle);
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                //System.out.println("tap" + newText);
                return false;
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void initSearchView(Menu menu, SearchView sv) {
        MenuItem item = menu.findItem(R.id.search_item);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            MenuItemCompat.setShowAsAction(item, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
            MenuItemCompat.setActionView(item, sv);
        }
        else {
            item.setShowAsAction(item.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | item.SHOW_AS_ACTION_IF_ROOM);
            item.setActionView(sv);
        }
    }
/*
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_item:
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
                /*return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

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
