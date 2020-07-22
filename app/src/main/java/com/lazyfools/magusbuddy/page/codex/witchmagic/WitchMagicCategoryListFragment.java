package com.lazyfools.magusbuddy.page.codex.witchmagic;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.WitchMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.page.codex.GroupListAdapter;
import com.lazyfools.magusbuddy.page.codex.GroupListItem;
import com.lazyfools.magusbuddy.page.common.SearchableRecycleViewFragment;
import com.lazyfools.magusbuddy.viewmodel.WitchMagicDatabaseViewModel;

import java.util.List;

public class WitchMagicCategoryListFragment extends SearchableRecycleViewFragment
{
    private WitchMagicDatabaseViewModel _viewModel;
    private GroupListAdapter<NameEntityOnClickListener> _adapter;

    public WitchMagicCategoryListFragment() { }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(WitchMagicDatabaseViewModel.class);
        _adapter = new GroupListAdapter<>(getActivity(),_listener);
        _recyclerView.setAdapter(_adapter);

        final Fragment fragment = this;

        _viewModel.getAllWitchMagicTypes().observe(fragment, new Observer<List<WitchMagicType>>() {
            @Override
            public void onChanged(@Nullable List<WitchMagicType> typeList) {
                assert typeList != null;
                _adapter.setSize(typeList.size());
                for (final WitchMagicType type : typeList){
                    _viewModel.getAllWitchMagicNamesOfType(type.type).observe(fragment, new Observer<List<NameEntity>>() {
                        @Override
                        public void onChanged(@Nullable final List<NameEntity> names) {
                            _adapter.setItem(type.type.ordinal(), new GroupListItem(type.type.toString(), names));
                        }
                    });
                }
            }
        });

    }

    @Override
    public void filterAdapterContent(String filterText) {
        _adapter.filter(filterText);
    }

    @Override
    protected int onItemClickNavigateTo() {
        return R.id.action_witchMagicCategoryListFragment_to_witchMagicSingleFragment;
    }
}
