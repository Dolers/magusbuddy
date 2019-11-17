package com.lazyfools.magusbuddy.page.codex.bardmagic;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.viewmodel.BardMagicDatabaseViewModel;

public class BardMagicSingleFragment extends Fragment {
    private BardMagicDatabaseViewModel _viewModel;

    public BardMagicSingleFragment() {
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(BardMagicDatabaseViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bardmagic_single_show, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneBardMagicByID(id).observe(this, new Observer<BardMagicEntity>() {
            @Override
            public void onChanged(@Nullable final BardMagicEntity BardMagic) {
                populateWith(BardMagic);
            }
        });
    }

    private void populateWith(BardMagicEntity bardMagic) {
        TextView titleTextView = getView().findViewById(R.id.name_textview);
        titleTextView.setText(bardMagic.getName());

        populateWithStats(bardMagic);

        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(bardMagic.getDescription());
    }

    private void populateWithStats(BardMagicEntity bardMagic) {
        TextView mpValue = getView().findViewById(R.id.mp_value);
        mpValue.setText(Integer.toString(bardMagic.getMp()));

        TextView empValue = getView().findViewById(R.id.emp_value);
        empValue.setText(Integer.toString(bardMagic.getEmp()));

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(bardMagic.getCastTime());

        TextView rangeValue = getView().findViewById(R.id.range_value);
        rangeValue.setText(bardMagic.getRange());

        TextView durationTimeValue = getView().findViewById(R.id.durationtime_value);
        durationTimeValue.setText(bardMagic.getDurationTime());
    }
}
