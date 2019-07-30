package com.lazyfools.magusbuddy;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.navigation.Navigation;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.SearchActivity;
import com.lazyfools.magusbuddy.database.entity.QualificationName;
import com.lazyfools.magusbuddy.page.skill.QualificationActivity;

import java.util.List;

import ir.mirrajabi.searchdialog.core.Searchable;

public class SkillSearchActivity extends SearchActivity {

    private ListView listView;

    SkillSearchActivity() {
        super(R.layout.skills_search_listitem);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skills_search_activity);

        listView = findViewById(R.id.listView);
        listView.setAdapter(mSearchAdapter);

        handleIntent(getIntent());
    }

    @Override
    protected void onItemClicked(Searchable item) {
        Intent intent = new Intent(this, QualificationActivity.class);
        intent.putExtra(getResources().getString(R.string.QUALIFICATION_ID), ((QualificationName)item).getId());
        startActivity(intent);
        //destroy this activity
        finish();
    }

    @Override protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    protected void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }

    protected void doMySearch(final String query){
        Log.i("query to find: ", query);
        mViewModel.getQualificationNamesOfFilter(query).observe(this, new Observer<List<QualificationName>>() {
            @Override
            public void onChanged(@Nullable final List<QualificationName> qualificationNamesOfFilter) {
                // Update the cached copy of the words in the adapter.
                mSearchAdapter.setItems(qualificationNamesOfFilter);
            }
        });
    }
}
