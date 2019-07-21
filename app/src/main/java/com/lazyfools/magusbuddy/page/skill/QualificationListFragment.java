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
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

import java.util.List;

public class QualificationListFragment extends Fragment {
    private QualificationListAdapter mAdapter;
    private QualificationListFragment.onClickListener mListener;

    // TODO: Customize parameters
    private DatabaseViewModel mViewModel;

    public QualificationListFragment(){}

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        String typeName = getArguments().getString(getResources().getString(R.string.QUALIFICATION_TYPENAME));

        QualificationEntity.QualificationTypeEnum typeFilter = QualificationEntity.QualificationTypeEnum.valueOf(typeName);

        mViewModel.getAllQualificationsOfType(typeFilter).observe(this, new Observer<List<QualificationEntity>>() {
            @Override
            public void onChanged(@Nullable final List<QualificationEntity> qualifications) {
                // Update the cached copy of the words in the adapter.
                mAdapter.setItems(qualifications);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final RecyclerView recyclerView = new RecyclerView(container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListener = new onClickListener(){
            @Override
            public void onClick(QualificationEntity item) {
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.QUALIFICATION_ID), item.getId());
                Navigation.findNavController(recyclerView).navigate(R.id.action_qualificationListFragment_to_qualificationActivity,bundle);
            }
        };
        mAdapter = new QualificationListAdapter(mListener);
        recyclerView.setAdapter(mAdapter);

        return recyclerView;
    }

    public interface onClickListener {
        void onClick(QualificationEntity item);
    }
}
