package com.lazyfools.magusbuddy;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ir.mirrajabi.searchdialog.core.Searchable;


public abstract class SearchActivity extends AppCompatActivity{

    protected DatabaseViewModel mViewModel;
    protected SearchAdapter mSearchAdapter;
    protected final int mLayoutResource;
    SearchActivity.onClickListener mListener;

    public SearchActivity(int layoutResource){
        mLayoutResource = layoutResource;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        mListener = new onClickListener(){
            @Override
            public void onClick(Searchable item) {
                onItemClicked(item);
            }
        };
        mSearchAdapter = new SearchAdapter(mLayoutResource,mListener);
    }

    protected abstract void onItemClicked(Searchable item);



    protected abstract void doMySearch(final String query);

    public interface onClickListener {
        void onClick(Searchable item);
    }
}
