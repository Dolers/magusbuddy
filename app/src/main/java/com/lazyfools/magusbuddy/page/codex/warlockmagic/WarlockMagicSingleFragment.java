package com.lazyfools.magusbuddy.page.codex.warlockmagic;

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
import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.page.common.DescTableAdapter;
import com.lazyfools.magusbuddy.utility.MarginItemDecoration;
import com.lazyfools.magusbuddy.viewmodel.WarlockMagicDatabaseViewModel;

import java.util.ArrayList;

public class WarlockMagicSingleFragment extends Fragment {
    private WarlockMagicDatabaseViewModel _viewModel;

    public WarlockMagicSingleFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(WarlockMagicDatabaseViewModel.class);
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

        _viewModel.getOneWarlockMagicByID(id).observe(this, new Observer<WarlockMagicEntity>() {
            @Override
            public void onChanged(@Nullable final WarlockMagicEntity WarlockMagic) {
                populateWith(WarlockMagic);
            }
        });
    }

    private void populateWith(WarlockMagicEntity warlockMagic) {
        TextView titleTextView = getView().findViewById(R.id.name_textview);
        titleTextView.setText(warlockMagic.getName());

        populateWithStats(warlockMagic);

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(warlockMagic.getDescription());

        TextView specialTextView = getView().findViewById(R.id.special);
        specialTextView.setText(warlockMagic.getSpecial());

        populateWithTables(warlockMagic);
    }

    private void populateWithStats(WarlockMagicEntity warlockMagic) {
        TextView mpValue = getView().findViewById(R.id.mp_value);
        mpValue.setText(Integer.toString(warlockMagic.getMp()));

        TextView empValue = getView().findViewById(R.id.emp_value);
        empValue.setText(Integer.toString(warlockMagic.getEmp()));

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(warlockMagic.getCastTime());

        TextView rangeValue = getView().findViewById(R.id.range_value);
        rangeValue.setText(warlockMagic.getRange());

        TextView durationTimeValue = getView().findViewById(R.id.durationtime_value);
        durationTimeValue.setText(warlockMagic.getDurationTime());
    }

    private void populateWithTables(WarlockMagicEntity warlockMagic) {
        ArrayList<String> descriptionTables = warlockMagic.getDescTables();
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
