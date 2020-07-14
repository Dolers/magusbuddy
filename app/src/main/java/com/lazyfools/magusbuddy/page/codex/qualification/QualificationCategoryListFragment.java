package com.lazyfools.magusbuddy.page.codex.qualification;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;
import com.lazyfools.magusbuddy.page.codex.GroupListAdapter;
import com.lazyfools.magusbuddy.page.codex.GroupListItem;
import com.lazyfools.magusbuddy.page.common.SearchableRecycleViewFragment;
import com.lazyfools.magusbuddy.viewmodel.DatabaseViewModel;

import java.util.List;

public class QualificationCategoryListFragment extends SearchableRecycleViewFragment
{
    private DatabaseViewModel _viewModel;
    private GroupListAdapter<NameEntityOnClickListener> _adapter;

    public QualificationCategoryListFragment() { }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        _viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        _adapter = new GroupListAdapter<>(getActivity(),_listener);
        _recyclerView.setAdapter(_adapter);

        final Fragment fragment = this;

        _viewModel.getAllQualificationTypes().observe(fragment, new Observer<List<QualificationType>>() {
            @Override
            public void onChanged(@Nullable List<QualificationType> typeList) {
                assert typeList != null;
                for (final QualificationType type : typeList){
                    _viewModel.getAllQualificationNamesOfType(type.type).observe(fragment, new Observer<List<NameEntity>>() {
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
        return R.id.action_qualificationCategoryListFragment_to_qualificationSingleFragment;
    }
}
