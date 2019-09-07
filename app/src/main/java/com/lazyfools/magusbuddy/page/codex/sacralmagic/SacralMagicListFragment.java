package com.lazyfools.magusbuddy.page.codex.sacralmagic;


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

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.viewmodel.SacralMagicDatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.page.codex.NameListAdapter;

import java.util.List;

/**
 * Creates a list of SacralMagics for UI
 * Need a SacralMagic_TYPENAME or SacralMagic_FILTER as argument to work
 */
public class SacralMagicListFragment extends Fragment {
    private NameListAdapter _adapter;
    private SacralMagicListFragment.onClickListener _listener;

    // TODO: Customize parameters
    private SacralMagicDatabaseViewModel _viewModel;

    public SacralMagicListFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(SacralMagicDatabaseViewModel.class);
        initViewModel();
    }

    private void initViewModel() {
        if (getArguments() != null) {
            if (getArguments().containsKey(getResources().getString(R.string.HIGHMAGIC_TYPE)))
                initViewModelWithType();
            else if (getArguments().containsKey(getResources().getString(R.string.HIGHMAGIC_FILTER))) {
                initViewModelWithFilter();
            }
        }
    }

    private void initViewModelWithType() {
        int typeOrdinal = getArguments().getInt(getResources().getString(R.string.HIGHMAGIC_TYPE));
        SacralMagicEntity.TypeEnum typeFilter = SacralMagicEntity.TypeEnum.values()[typeOrdinal];

        _viewModel.getAllSacralMagicNamesOfType(typeFilter).observe(this, new Observer<List<NameEntity>>() {
            @Override
            public void onChanged(@Nullable final List<NameEntity> nameEntities) {
                // Update the cached copy of the words in the adapter.
                _adapter.setItems(nameEntities);
            }
        });
    }

    private void initViewModelWithFilter() {
        String filter = getArguments().getString(getResources().getString(R.string.HIGHMAGIC_FILTER));

        _viewModel.getSacralMagicNamesOfFilter(filter).observe(this, new Observer<List<NameEntity>>() {
            @Override
            public void onChanged(@Nullable final List<NameEntity> nameEntities) {
                // Update the cached copy of the words in the adapter.
                _adapter.setItems(nameEntities);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        _listener = new onClickListener(){
            @Override
            public void onClick(NameEntity item) {
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.HIGHMAGIC_ID), item.getId());
                Navigation.findNavController(recyclerView).navigate(R.id.action_sacralMagicListFragment_to_sacralMagicSingleFragment,bundle);
            }
        };
        _adapter = new NameListAdapter(_listener);
        recyclerView.setAdapter(_adapter);

        return recyclerView;
    }

    public interface onClickListener extends com.lazyfools.magusbuddy.page.codex.onClickListener<NameEntity>{ }
}
