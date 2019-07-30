package com.lazyfools.magusbuddy.page.skill;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

import java.util.ArrayList;

import hakobastvatsatryan.DropdownTextView;


public class QualificationActivity extends AppCompatActivity {
    private DatabaseViewModel mViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qualification_single_show);

        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        Integer id = getIntent().getIntExtra(getResources().getString(R.string.QUALIFICATION_ID),0);
        mViewModel.getOneQualificationByID(id).observe(this, new Observer<QualificationEntity>() {
            @Override
            public void onChanged(@Nullable final QualificationEntity qualification) {
                populateWith(qualification);
            }
        });
    }

    private void populateWith(QualificationEntity qualification) {
        TextView titleTextView = findViewById(R.id.skill_name_textview);
        titleTextView.setText(qualification.getName());

        TextView descriptionTextView = findViewById(R.id.skill_description);
        descriptionTextView.setText(qualification.getDescription());

        populateWithTables(qualification);
        populateLevelDescriptionDropdownViews(qualification);
    }

    private void populateWithTables(QualificationEntity qualification) {
        RecyclerView tableListView = findViewById(R.id.table_listview);
        ArrayList<String> descriptionTables = qualification.getDescriptionTables();
        if (descriptionTables.isEmpty()){
            tableListView.setVisibility(View.INVISIBLE);
        }
        else {
            QualificationDescTableAdapter adapter = new QualificationDescTableAdapter(getApplicationContext());
            adapter.setItems(qualification.getDescriptionTables());
            tableListView.setAdapter(adapter);
        }
    }

    private void populateLevelDescriptionDropdownViews(QualificationEntity qualification) {
        DropdownTextView firstLevelTextView = findLevelDropdownTextviewById(R.id.first_level_dropdown_textview);
        DropdownTextView secondLevelTextView = findLevelDropdownTextviewById(R.id.second_level_dropdown_textview);
        DropdownTextView thirdLevelTextView = findLevelDropdownTextviewById(R.id.third_level_dropdown_textview);
        DropdownTextView fourthLevelTextView = findLevelDropdownTextviewById(R.id.fourth_level_dropdown_textview);
        DropdownTextView fifthLevelTextView = findLevelDropdownTextviewById(R.id.fifth_level_dropdown_textview);

        if (qualification.getFirstLevelDesc() == null){
            firstLevelTextView.setVisibility(View.INVISIBLE);
            secondLevelTextView.setVisibility(View.INVISIBLE);
            thirdLevelTextView.setVisibility(View.INVISIBLE);
            fourthLevelTextView.setVisibility(View.INVISIBLE);
            fifthLevelTextView.setVisibility(View.INVISIBLE);
        }
        else {
            firstLevelTextView.setTitleText(R.string.first_level);
            firstLevelTextView.setContentText(qualification.getFirstLevelDesc());

            secondLevelTextView.setTitleText(R.string.second_level);
            secondLevelTextView.setContentText(qualification.getSecondLevelDesc());

            thirdLevelTextView.setTitleText(R.string.third_level);
            thirdLevelTextView.setContentText(qualification.getThirdLevelDesc());

            fourthLevelTextView.setTitleText(R.string.fourth_level);
            fourthLevelTextView.setContentText(qualification.getFourthLevelDesc());

            fifthLevelTextView.setTitleText(R.string.fifth_level);
            fifthLevelTextView.setContentText(qualification.getFifthLevelDesc());
        }
    }
    private DropdownTextView findLevelDropdownTextviewById(int resId){
        View test1View = findViewById(resId);
        return test1View.findViewById(R.id.level_dropdown_textview);
    }
}
