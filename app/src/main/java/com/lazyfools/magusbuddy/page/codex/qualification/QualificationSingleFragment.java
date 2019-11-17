package com.lazyfools.magusbuddy.page.codex.qualification;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.viewmodel.DatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.page.codex.DescTableAdapter;
import com.lazyfools.magusbuddy.utility.MarginItemDecoration;

import java.util.ArrayList;

import hakobastvatsatryan.DropdownTextView;

public class QualificationSingleFragment extends Fragment {
    private DatabaseViewModel _viewModel;

    public QualificationSingleFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(DatabaseViewModel.class);
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
        TextView titleTextView = getView().findViewById(R.id.name_textview);
        titleTextView.setText(qualification.getName());

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(qualification.getDescription());

        populateWithTables(qualification);
        populateLevelDescriptionDropdownViews(qualification);
    }

    private void populateWithTables(QualificationEntity qualification) {
        RecyclerView tableListView = getView().findViewById(R.id.table_listview);
        tableListView.addItemDecoration(new MarginItemDecoration(30,30,0,0));
        ArrayList<String> descriptionTables = qualification.getDescriptionTables();
        if (descriptionTables.isEmpty()){
            tableListView.setVisibility(View.GONE);
        }
        else {
            DescTableAdapter adapter = new DescTableAdapter(getActivity().getApplicationContext());
            adapter.setItems(qualification.getDescriptionTables());
            tableListView.setAdapter(adapter);
        }
    }

    private void populateLevelDescriptionDropdownViews(QualificationEntity qualification) {
        DropdownTextView firstLevelTextView = findLevelDropdownTextViewById(R.id.first_level_dropdown_textview);
        DropdownTextView secondLevelTextView = findLevelDropdownTextViewById(R.id.second_level_dropdown_textview);
        DropdownTextView thirdLevelTextView = findLevelDropdownTextViewById(R.id.third_level_dropdown_textview);
        DropdownTextView fourthLevelTextView = findLevelDropdownTextViewById(R.id.fourth_level_dropdown_textview);
        DropdownTextView fifthLevelTextView = findLevelDropdownTextViewById(R.id.fifth_level_dropdown_textview);

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
    private DropdownTextView findLevelDropdownTextViewById(int resId){
        View test1View = getView().findViewById(resId);
        return test1View.findViewById(R.id.level_dropdown_textview);
    }
}
