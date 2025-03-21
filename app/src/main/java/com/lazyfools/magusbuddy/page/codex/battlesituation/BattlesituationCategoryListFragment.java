package com.lazyfools.magusbuddy.page.codex.battlesituation;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.page.codex.GroupListAdapter;
import com.lazyfools.magusbuddy.page.codex.GroupListItem;
import com.lazyfools.magusbuddy.page.common.SearchableRecycleViewFragment;
import com.lazyfools.magusbuddy.viewmodel.BattlesituationDatabaseViewModel;

import java.util.List;

public class BattlesituationCategoryListFragment extends SearchableRecycleViewFragment
{
    private BattlesituationDatabaseViewModel _viewModel;
    private GroupListAdapter<NameEntityOnClickListener> _adapter;

    public BattlesituationCategoryListFragment() { }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(BattlesituationDatabaseViewModel.class);
        _adapter = new GroupListAdapter<>(getActivity(),_listener);
        _recyclerView.setAdapter(_adapter);

        final Fragment fragment = this;

        _adapter.setSize(1);
        _viewModel.getAllBattlesituationNames().observe(fragment, new Observer<List<NameEntity>>() {
            @Override
            public void onChanged(@Nullable final List<NameEntity> names) {
                _adapter.setItem(0, new GroupListItem("Harc", names));
            }
        });
    }

    @Override
    public void filterAdapterContent(String filterText) {
        _adapter.filter(filterText);
    }

    @Override
    protected int onItemClickNavigateTo() {
        return R.id.action_battlesituationCategoryListFragment_to_battlesituationSingleFragment;
    }
}
