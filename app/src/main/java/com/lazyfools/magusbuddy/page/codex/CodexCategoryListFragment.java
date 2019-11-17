package com.lazyfools.magusbuddy.page.codex;


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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.viewmodel.DatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;

import java.util.List;

/**
 * A fragment representing a list of picture items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link onClickListener}
 * interface.
 */
public class CodexCategoryListFragment extends Fragment {

    private CodexCategoryOnClickListener _listener;
    private DatabaseViewModel _viewModel;
    private CodexCategoryListAdapter _adapter;

    public CodexCategoryListFragment() { }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        _viewModel.getAllCodex().observe(this, new Observer<List<CodexEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CodexEntity> entities) {
                // Update the cached copy of the words in the adapter.
                _adapter.setItems(entities);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(container.getContext())
                .inflate(R.layout.category_list, container, false);
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(),2));

        Intent intent = getActivity().getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //TODO do smth with query
        }
        _listener = new CodexCategoryOnClickListener(){
            @Override
            public void onClick(CodexEntity item) {

                Navigation.findNavController(recyclerView).navigate(getNavigateAction(item));
            }
        };
        _adapter = new CodexCategoryListAdapter(_listener,container.getContext());
        recyclerView.setAdapter(_adapter);

        return recyclerView;
    }

    private int getNavigateAction(CodexEntity item) {
        //TODO
        switch (item.getTable()){
            case QUALIFICATION: return R.id.action_navigation_codex_to_qualificationCategoryListFragment;
            case BARDMAGIC: return R.id.action_navigation_codex_to_bardMagicCategoryListFragment;
            case WITCHMAGIC:
            case WARLOCKMAGIC:
            case HIGHMAGIC: return R.id.action_navigation_codex_to_highMagicCategoryListFragment;
            case PSZI: return R.id.action_navigation_codex_to_psziMagicCategoryListFragment;
            case SACRALMAGIC: return R.id.action_navigation_codex_to_sacralMagicCategoryListFragment;
            case FIREMAGEMAGIC:
        }
        return R.id.action_navigation_codex_to_qualificationCategoryListFragment;
    }

    /*
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
                bundle.putString(getResources().getString(R.string.QUALIFICATION_FILTER), query);
                Navigation.findNavController(_recyclerView).navigate(R.id.action_qualificationCategoryListFragment_to_qualificationListFragment,bundle);
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {
                //System.out.println("tap" + newText);
                return false;
            }
        });
    }*/

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
    public interface CodexCategoryOnClickListener extends onClickListener<CodexEntity> {}
}
