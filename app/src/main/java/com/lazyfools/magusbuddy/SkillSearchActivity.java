package com.lazyfools.magusbuddy;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lazyfools.magusbuddy.database.entity.QualificationName;

import java.util.List;

public class SkillSearchActivity extends AppCompatActivity{

    private DatabaseViewModel mViewModel;
    private SkillSearchAdapter mSearchAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.skills_search_activity);

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO start activity showing only the skill
            }
        });

        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);

        mSearchAdapter = new SkillSearchAdapter();
        listView.setAdapter(mSearchAdapter);

        handleIntent(getIntent());

    }


    @Override protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }


    private void doMySearch(final String query){
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
