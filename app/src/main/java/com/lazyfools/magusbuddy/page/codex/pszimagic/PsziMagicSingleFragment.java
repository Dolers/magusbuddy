package com.lazyfools.magusbuddy.page.codex.pszimagic;

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
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.page.common.DescTableAdapter;
import com.lazyfools.magusbuddy.utility.MarginItemDecoration;
import com.lazyfools.magusbuddy.viewmodel.PsziMagicDatabaseViewModel;

import java.util.ArrayList;

public class PsziMagicSingleFragment extends Fragment {
    private PsziMagicDatabaseViewModel _viewModel;

    public PsziMagicSingleFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(PsziMagicDatabaseViewModel.class);
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

        _viewModel.getOnePsziMagicByID(id).observe(this, new Observer<PsziMagicEntity>() {
            @Override
            public void onChanged(@Nullable final PsziMagicEntity PsziMagic) {
                populateWith(PsziMagic);
            }
        });
    }

    private void populateWith(PsziMagicEntity psziMagic) {
        TextView titleTextView = getView().findViewById(R.id.name_textview);
        titleTextView.setText(psziMagic.getName());

        populateWithStats(psziMagic);

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(psziMagic.getDescription());

        populateWithTables(psziMagic);

        TextView specialTextView = getView().findViewById(R.id.special);
        specialTextView.setText(psziMagic.getSpecial());
    }

    private void populateWithStats(PsziMagicEntity psziMagic) {
        TextView mpTitle = getView().findViewById(R.id.mp);
        mpTitle.setText(getText(R.string.pszi));

        TextView mpValue = getView().findViewById(R.id.mp_value);
        mpValue.setText(Integer.toString(psziMagic.getMp()));

        TextView emp = getView().findViewById(R.id.emp);
        if (psziMagic.getEmpText() != null)
            emp.setText(psziMagic.getEmpText());
        else
            emp.setText(R.string.epszi);

        TextView empValue = getView().findViewById(R.id.emp_value);
        empValue.setText(Integer.toString(psziMagic.getEmp()));

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(psziMagic.getCastTime());

        TextView durationTimeValue = getView().findViewById(R.id.durationtime_value);
        durationTimeValue.setText(psziMagic.getDurationTime());

        if (psziMagic.getMagicResistance() != null) {
            TextView magicResistance = getView().findViewById(R.id.resistance);
            magicResistance.setVisibility(View.VISIBLE);
            TextView magicResistanceValue = getView().findViewById(R.id.resistance_value);
            magicResistanceValue.setVisibility(View.VISIBLE);
            magicResistanceValue.setText(psziMagic.getMagicResistance());
        }
    }

    private void populateWithTables(PsziMagicEntity psziMagic) {
        ArrayList<String> descriptionTables = psziMagic.getDescTables();
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
