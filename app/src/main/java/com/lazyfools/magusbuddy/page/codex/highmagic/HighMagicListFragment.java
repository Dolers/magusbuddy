package com.lazyfools.magusbuddy.page.codex.highmagic;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.viewmodel.HighMagicDatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.page.codex.NameListAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Creates a list of HighMagics for UI
 * Need a HighMagic_TYPENAME or HighMagic_FILTER as argument to work
 */
public class HighMagicListFragment extends Fragment {
    private NameListAdapter _adapter;
    private HighMagicListFragment.onClickListener _listener;

    // TODO: Customize parameters
    private HighMagicDatabaseViewModel _viewModel;

    public HighMagicListFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(HighMagicDatabaseViewModel.class);
        initViewModel();
    }

    private void initViewModel() {
        if (getArguments() != null) {
            if (getArguments().containsKey(getResources().getString(R.string.SKILL_TYPE)))
                initViewModelWithType();
            else if (getArguments().containsKey(getResources().getString(R.string.SKILL_NAME_FILTER))) {
                initViewModelWithFilter();
            }
        }
    }

    private void initViewModelWithType() {
        int typeOrdinal = getArguments().getInt(getResources().getString(R.string.SKILL_TYPE));
        HighMagicEntity.TypeEnum typeFilter = HighMagicEntity.TypeEnum.values()[typeOrdinal];

        _viewModel.getAllHighMagicNamesOfType(typeFilter).observe(this, new Observer<List<NameEntity>>() {
            @Override
            public void onChanged(@Nullable final List<NameEntity> nameEntities) {
                // Update the cached copy of the words in the adapter.
                _adapter.setItems(nameEntities);
            }
        });
    }

    private void initViewModelWithFilter() {
        String filter = getArguments().getString(getResources().getString(R.string.SKILL_NAME_FILTER));

        _viewModel.getHighMagicNamesOfFilter(filter).observe(this, new Observer<List<NameEntity>>() {
            @Override
            public void onChanged(@Nullable final List<NameEntity> nameEntities) {
                // Update the cached copy of the words in the adapter.
                _adapter.setItems(nameEntities);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(container.getContext())
                .inflate(R.layout.name_list, container, false);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        _listener = new onClickListener(){
            @Override
            public void onClick(NameEntity item) {
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.SKILL_ID), item.getId());

                Navigation.findNavController(recyclerView).navigate(R.id.action_highMagicListFragment_to_highMagicSingleFragment,bundle);
            }
        };
        _adapter = new NameListAdapter(_listener);
        recyclerView.setAdapter(_adapter);

        return recyclerView;
    }

    public interface onClickListener extends com.lazyfools.magusbuddy.page.codex.onClickListener<NameEntity>{ }
}
