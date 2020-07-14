package com.lazyfools.magusbuddy.page.codex.firemagic;

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
import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;
import com.lazyfools.magusbuddy.page.common.DescTableAdapter;
import com.lazyfools.magusbuddy.utility.MarginItemDecoration;
import com.lazyfools.magusbuddy.viewmodel.FireMagicDatabaseViewModel;

import java.util.ArrayList;

public class FireMagicSingleFragment extends Fragment {
    private FireMagicDatabaseViewModel _viewModel;

    public FireMagicSingleFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(FireMagicDatabaseViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.firemagic_single_show, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneFireMagicByID(id).observe(this, new Observer<FireMagicEntity>() {
            @Override
            public void onChanged(@Nullable final FireMagicEntity FireMagic) {
                populateWith(FireMagic);
            }
        });
    }

    private void populateWith(FireMagicEntity fireMagic) {
        TextView titleTextView = getView().findViewById(R.id.name_textview);
        titleTextView.setText(fireMagic.getName());

        populateWithStats(fireMagic);

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(fireMagic.getDescription());

        TextView specialTextView = getView().findViewById(R.id.special);
        specialTextView.setText(fireMagic.getSpecial());

        populateWithTables(fireMagic);
    }

    private void populateWithStats(FireMagicEntity fireMagic) {
        TextView mpValue = getView().findViewById(R.id.mp_value);
        mpValue.setText(Integer.toString(fireMagic.getMp()));

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(fireMagic.getCastTime());

        TextView durationTimeValue = getView().findViewById(R.id.durationtime_value);
        durationTimeValue.setText(fireMagic.getDurationTime());
    }

    private void populateWithTables(FireMagicEntity fireMagic) {
        RecyclerView tableListView = getView().findViewById(R.id.table_listview);
        tableListView.addItemDecoration(new MarginItemDecoration(30,30,0,0));
        ArrayList<String> descriptionTables = fireMagic.getDescTables();
        if (descriptionTables.isEmpty()){
            tableListView.setVisibility(View.GONE);
        }
        else {
            DescTableAdapter adapter = new DescTableAdapter(getActivity().getApplicationContext());
            adapter.setItems(fireMagic.getDescTables());
            tableListView.setAdapter(adapter);
        }
    }
}
