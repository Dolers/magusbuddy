package com.lazyfools.magusbuddy.page.common;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

abstract public class SearchableRecycleViewFragment extends SearchableFragment
{
    protected NameEntityOnClickListener _listener;
    protected RecyclerView _recyclerView;

    protected abstract int onItemClickNavigateTo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        _recyclerView = (RecyclerView) LayoutInflater.from(container.getContext())
                .inflate(R.layout.grouplistview, container, false);

        _recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        _listener = new NameEntityOnClickListener() {
            @Override
            public void onClick(NameEntity item) {
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.SKILL_ID), item.getId());
                Navigation.findNavController(_recyclerView).navigate(onItemClickNavigateTo(),bundle);
            }
        };

        return _recyclerView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _listener = null;
    }

    public interface NameEntityOnClickListener extends onClickListener<NameEntity> {
        void onClick(NameEntity item);
    }
}
