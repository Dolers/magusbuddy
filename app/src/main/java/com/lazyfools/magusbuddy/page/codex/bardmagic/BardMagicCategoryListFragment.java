package com.lazyfools.magusbuddy.page.codex.bardmagic;


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

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.BardMagicType;
import com.lazyfools.magusbuddy.page.codex.onClickListener;
import com.lazyfools.magusbuddy.viewmodel.BardMagicDatabaseViewModel;

import java.util.List;

/**
 * A fragment representing a list of picture items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onClickListener}
 * interface.
 */
public class BardMagicCategoryListFragment extends Fragment {

    private BardMagicTypeOnClickListener _listener;
    private BardMagicDatabaseViewModel _viewModel;
    private BardMagicCategoryListAdapter _adapter;
    private RecyclerView _recyclerView;

    public BardMagicCategoryListFragment() { }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(BardMagicDatabaseViewModel.class);

        _viewModel.getAllBardMagicTypes().observe(this, new Observer<List<BardMagicType>>() {
            @Override
            public void onChanged(@Nullable final List<BardMagicType> BardMagics) {
                // Update the cached copy of the words in the adapter.
                _adapter.setItems(BardMagics);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        _recyclerView = (RecyclerView) LayoutInflater.from(container.getContext())
                .inflate(R.layout.category_list, container, false);
        _recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        Intent intent = getActivity().getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO do smth with query
        }
        _listener = new BardMagicTypeOnClickListener(){
            @Override
            public void onClick(BardMagicType item) {
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.SKILL_TYPE), item.getType().ordinal());
                Navigation.findNavController(_recyclerView).navigate(R.id.action_bardMagicCategoryListFragment_to_bardMagicListFragment,bundle);
            }
        };
        _adapter = new BardMagicCategoryListAdapter(_listener,container.getContext());
        _recyclerView.setAdapter(_adapter);

        return _recyclerView;
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
                sv.clearFocus();
                Bundle bundle = new Bundle();
                bundle.putString(getResources().getString(R.string.SKILL_NAME_FILTER), query);
                Navigation.findNavController(_recyclerView).navigate(R.id.action_bardMagicCategoryListFragment_to_bardMagicListFragment,bundle);
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
                List<NameEntity> names = _viewModel.getAllBardMagicNames().getValue();

                SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat<NameEntity>(getActivity(), "Search...",
                        "What are you looking for...?", null, (ArrayList) names,
                        new SearchResultListener<NameEntity>() {
                            @Override
                            public void onSelected(
                                    BaseSearchDialogCompat dialog,
                                    NameEntity item, int position
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
    public interface BardMagicTypeOnClickListener extends onClickListener<BardMagicType> {
        void onClick(BardMagicType item);
    }
}
