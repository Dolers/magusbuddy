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
        return inflater.inflate(R.layout.sacralmagic_single_show, null);
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
        TextView mpValue = getView().findViewById(R.id.kp_value);
        mpValue.setText(Integer.toString(sacralMagic.getKp()));

        TextView empValue = getView().findViewById(R.id.ekp_value);
        empValue.setText(Integer.toString(sacralMagic.getEkp()));

        if (sacralMagic.getEkpText() != null) {
            TextView ekp = getView().findViewById(R.id.ekp);
            ekp.setText(sacralMagic.getEkpText());
        }

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(sacralMagic.getCastTime());

        TextView rangeValue = getView().findViewById(R.id.range_value);
        rangeValue.setText(sacralMagic.getRange());

        TextView durationTimeValue = getView().findViewById(R.id.durationtime_value);
        durationTimeValue.setText(sacralMagic.getDurationTime());
    }

    private void populateWithTables(SacralMagicEntity sacralMagic) {
        RecyclerView tableListView = getView().findViewById(R.id.table_listview);
        tableListView.addItemDecoration(new MarginItemDecoration(30,30,0,0));
        ArrayList<String> descriptionTables = sacralMagic.getDescTables();
        if (descriptionTables.isEmpty()){
            tableListView.setVisibility(View.GONE);
        }
        else {
            DescTableAdapter adapter = new DescTableAdapter(getActivity().getApplicationContext());
            adapter.setItems(sacralMagic.getDescTables());
            tableListView.setAdapter(adapter);
        }
    }
}
