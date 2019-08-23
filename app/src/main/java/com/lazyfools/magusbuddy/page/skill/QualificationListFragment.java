package com.lazyfools.magusbuddy.page.skill;


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

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationName;

import java.util.List;

/**
 * Creates a list of qualifications for UI
 * Need a QUALIFICATION_TYPENAME or QUALIFICATION_FILTER as argument to work
 */
public class QualificationListFragment extends Fragment {
    private QualificationListAdapter mAdapter;
    private QualificationListFragment.onClickListener mListener;

    // TODO: Customize parameters
    private DatabaseViewModel mViewModel;

    public QualificationListFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.VISIBLE);

        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        initViewModel();
    }

    private void initViewModel() {
        String arg = getArguments().getString(getResources().getString(R.string.QUALIFICATION_TYPENAME));
        if (arg == null){
            arg = getArguments().getString(getResources().getString(R.string.QUALIFICATION_FILTER));
            mViewModel.getQualificationNamesOfFilter(arg).observe(this, new Observer<List<QualificationName>>() {
                @Override
                public void onChanged(@Nullable final List<QualificationName> qualificationNames) {
                    // Update the cached copy of the words in the adapter.
                    mAdapter.setItems(qualificationNames);
                }
            });
        }
        else {
            QualificationEntity.QualificationTypeEnum typeFilter = QualificationEntity.QualificationTypeEnum.valueOf(arg);

            mViewModel.getAllQualificationNamesOfType(typeFilter).observe(this, new Observer<List<QualificationName>>() {
                @Override
                public void onChanged(@Nullable final List<QualificationName> qualificationNames) {
                    // Update the cached copy of the words in the adapter.
                    mAdapter.setItems(qualificationNames);
                }
            });
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListener = new onClickListener(){
            @Override
            public void onClick(QualificationName item) {
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.QUALIFICATION_ID), item.getId());
                Navigation.findNavController(recyclerView).navigate(R.id.action_qualificationListFragment_to_qualificationSingleFragment,bundle);
            }
        };
        mAdapter = new QualificationListAdapter(mListener);
        recyclerView.setAdapter(mAdapter);

        return recyclerView;
    }

    public interface onClickListener {
        void onClick(QualificationName item);
    }
}
