package com.lazyfools.magusbuddy.page.codex.qualification;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.page.codex.SingleFragment;
import com.lazyfools.magusbuddy.viewmodel.DatabaseViewModel;

import hakobastvatsatryan.DropdownTextView;

public class QualificationSingleFragment extends SingleFragment<DatabaseViewModel> {
    public QualificationSingleFragment() {
        super(DatabaseViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.qualification_single_show, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneQualificationByID(id).observe(this, new Observer<QualificationEntity>() {
            @Override
            public void onChanged(@Nullable final QualificationEntity qualification) {
                populateWith(qualification);
            }
        });
    }

    private void populateWith(QualificationEntity qualification) {
        TextView titleTextView = getView().findViewById(R.id.title_value);
        titleTextView.setText(qualification.getName());

        TextView typeTextView = getView().findViewById(R.id.type_value);
        typeTextView.setText(qualification.getType().toString());

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(qualification.getDescription());

        qualification.setDescriptionTables(populateWithTables());

        populateLevelDescriptionDropdownViews(qualification);
    }

    private void populateLevelDescriptionDropdownViews(QualificationEntity qualification) {
        DropdownTextView firstLevelTextView = findLevelDropdownTextViewById(R.id.first_level_dropdown_textview);
        DropdownTextView secondLevelTextView = findLevelDropdownTextViewById(R.id.second_level_dropdown_textview);
        DropdownTextView thirdLevelTextView = findLevelDropdownTextViewById(R.id.third_level_dropdown_textview);
        DropdownTextView fourthLevelTextView = findLevelDropdownTextViewById(R.id.fourth_level_dropdown_textview);
        DropdownTextView fifthLevelTextView = findLevelDropdownTextViewById(R.id.fifth_level_dropdown_textview);

        if (qualification.getFirstLevelDesc().isEmpty()){
            firstLevelTextView.setVisibility(View.GONE);
            secondLevelTextView.setVisibility(View.GONE);
            thirdLevelTextView.setVisibility(View.GONE);
            fourthLevelTextView.setVisibility(View.GONE);
            fifthLevelTextView.setVisibility(View.GONE);
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

    private DropdownTextView findLevelDropdownTextViewById(int resId){
        View test1View = getView().findViewById(resId);
        return test1View.findViewById(R.id.level_dropdown_textview);
    }
}
