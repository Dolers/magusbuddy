package com.lazyfools.magusbuddy.page.skill;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lazyfools.magusbuddy.DatabaseViewModel;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;

import hakobastvatsatryan.DropdownTextView;


public class QualificationActivity extends AppCompatActivity {
    public static final String QUALIFICATION_NAME = "QUALIFICATION_NAME";
    private DatabaseViewModel mViewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qualification_single_show);


        mViewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
        String name = getIntent().getStringExtra(QUALIFICATION_NAME);
        mViewModel.getOneQualificationOfFilter(name).observe(this, new Observer<QualificationEntity>() {
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

        populateLevelDescriptionDropdownViews(qualification);
    }

    private void populateLevelDescriptionDropdownViews(QualificationEntity qualification) {
        DropdownTextView firstLevelTextView = findViewById(R.id.first_level_dropdown_textview);
        DropdownTextView secondLevelTextView = findViewById(R.id.second_level_dropdown_textview);
        DropdownTextView thirdLevelTextView = findViewById(R.id.third_level_dropdown_textview);
        DropdownTextView fourthLevelTextView = findViewById(R.id.fourth_level_dropdown_textview);
        DropdownTextView fifthLevelTextView = findViewById(R.id.fifth_level_dropdown_textview);

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
}
