package com.lazyfools.magusbuddy.page.common;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.utility.BasicCallback;

abstract public class SearchableRecycleViewFragment extends SearchableFragment
{
    protected NameEntityOnClickListener _listener;
    protected RecyclerView _recyclerView;
    protected Parcelable _state;

    @Override
    public void onPause() {
        _state = _recyclerView.getLayoutManager().onSaveInstanceState();
        super.onPause();
    }

    @Override
    public void onStop() {
        _state = _recyclerView.getLayoutManager().onSaveInstanceState();
        super.onStop();
    }

    protected abstract int onItemClickNavigateTo();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        _recyclerView = (RecyclerView) LayoutInflater.from(container.getContext())
                .inflate(R.layout.grouplistview, container, false);

        _recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final SearchableFragment fragment = this;

        _listener = new NameEntityOnClickListener() {
            @Override
            public void onClick(NameEntity item) {
                Bundle bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.SKILL_ID), item.getId());
                Navigation.findNavController(fragment.getView()).navigate(onItemClickNavigateTo(),bundle);
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

    public BasicCallback restoreScrollCalback(){
        return new BasicCallback() {
            @Override
            public void callback() {
                if(_state != null) {
                    _recyclerView.getLayoutManager().onRestoreInstanceState(_state);
                }
            }
        };
    }
}
