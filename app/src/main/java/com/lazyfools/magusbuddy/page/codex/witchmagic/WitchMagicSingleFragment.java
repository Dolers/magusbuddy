package com.lazyfools.magusbuddy.page.codex.witchmagic;

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

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;
import com.lazyfools.magusbuddy.page.common.DescTableAdapter;
import com.lazyfools.magusbuddy.utility.MarginItemDecoration;
import com.lazyfools.magusbuddy.viewmodel.WitchMagicDatabaseViewModel;

import java.util.ArrayList;

public class WitchMagicSingleFragment extends Fragment {
    private WitchMagicDatabaseViewModel _viewModel;

    public WitchMagicSingleFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(WitchMagicDatabaseViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return LayoutInflater.from(container.getContext())
                .inflate(R.layout.magic_single_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneWitchMagicByID(id).observe(this, new Observer<WitchMagicEntity>() {
            @Override
            public void onChanged(@Nullable final WitchMagicEntity WitchMagic) {
                populateWith(WitchMagic);
            }
        });
    }

    private void populateWith(WitchMagicEntity witchMagic) {
        TextView titleTextView = getView().findViewById(R.id.name_textview);
        titleTextView.setText(witchMagic.getName());

        populateWithStats(witchMagic);

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(witchMagic.getDescription());

        TextView specialTextView = getView().findViewById(R.id.special);
        specialTextView.setText(witchMagic.getSpecial());

        populateWithTables(witchMagic);
    }

    private void populateWithStats(WitchMagicEntity witchMagic) {
        TextView mpValue = getView().findViewById(R.id.mp_value);
        mpValue.setText(Integer.toString(witchMagic.getMp()));

        TextView empValue = getView().findViewById(R.id.emp_value);
        empValue.setText(Integer.toString(witchMagic.getEmp()));

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(witchMagic.getCastTime());

        TextView rangeValue = getView().findViewById(R.id.range_value);
        rangeValue.setText(witchMagic.getRange());

        TextView durationTimeValue = getView().findViewById(R.id.durationtime_value);
        durationTimeValue.setText(witchMagic.getDurationTime());
    }

    private void populateWithTables(WitchMagicEntity witchMagic) {
        ArrayList<String> descriptionTables = witchMagic.getDescTables();
        if (!descriptionTables.isEmpty()){
            RecyclerView tableListView = getView().findViewById(R.id.table_listview);
            tableListView.setVisibility(View.VISIBLE);
            tableListView.addItemDecoration(new MarginItemDecoration(30,30,0,0));

            DescTableAdapter adapter = new DescTableAdapter(getActivity().getApplicationContext());
            adapter.setItems(descriptionTables);
            tableListView.setAdapter(adapter);
        }
    }
}
