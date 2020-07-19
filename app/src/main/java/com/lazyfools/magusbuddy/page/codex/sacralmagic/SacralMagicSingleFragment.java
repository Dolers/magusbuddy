package com.lazyfools.magusbuddy.page.codex.sacralmagic;

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

import com.lazyfools.magusbuddy.viewmodel.SacralMagicDatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.page.common.DescTableAdapter;
import com.lazyfools.magusbuddy.utility.MarginItemDecoration;

import java.util.ArrayList;

public class SacralMagicSingleFragment extends Fragment {
    private SacralMagicDatabaseViewModel _viewModel;

    public SacralMagicSingleFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(SacralMagicDatabaseViewModel.class);
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

        _viewModel.getOneSacralMagicByID(id).observe(this, new Observer<SacralMagicEntity>() {
            @Override
            public void onChanged(@Nullable final SacralMagicEntity SacralMagic) {
                populateWith(SacralMagic);
            }
        });
    }

    private void populateWith(SacralMagicEntity sacralMagic) {
        TextView titleTextView = getView().findViewById(R.id.name_textview);
        titleTextView.setText(sacralMagic.getName());

        populateWithStats(sacralMagic);

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(sacralMagic.getDescription());

        populateWithTables(sacralMagic);
    }

    private void populateWithStats(SacralMagicEntity sacralMagic) {
        TextView mpTitle = getView().findViewById(R.id.mp);
        mpTitle.setText(getText(R.string.kp));

        TextView mpValue = getView().findViewById(R.id.mp_value);
        mpValue.setText(Integer.toString(sacralMagic.getKp()));

        TextView ekp = getView().findViewById(R.id.emp);
        if (sacralMagic.getEkpText() != null)
            ekp.setText(sacralMagic.getEkpText());
        else
            ekp.setText(getText(R.string.ekp));

        TextView empValue = getView().findViewById(R.id.emp_value);
        empValue.setText(Integer.toString(sacralMagic.getEkp()));

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(sacralMagic.getCastTime());

        TextView rangeValue = getView().findViewById(R.id.range_value);
        rangeValue.setText(sacralMagic.getRange());

        TextView durationTimeValue = getView().findViewById(R.id.durationtime_value);
        durationTimeValue.setText(sacralMagic.getDurationTime());

        if (sacralMagic.getMagicResistance() != null) {
            TextView magicResistance = getView().findViewById(R.id.resistance);
            magicResistance.setVisibility(View.VISIBLE);
            TextView magicResistanceValue = getView().findViewById(R.id.resistance_value);
            magicResistanceValue.setVisibility(View.VISIBLE);
            magicResistanceValue.setText(sacralMagic.getMagicResistance());
        }
    }

    private void populateWithTables(SacralMagicEntity sacralMagic) {
        ArrayList<String> descriptionTables = sacralMagic.getDescTables();
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
