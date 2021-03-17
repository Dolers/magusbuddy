package com.lazyfools.magusbuddy.page.codex.sacralmagic;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.lazyfools.magusbuddy.page.codex.SingleFragment;
import com.lazyfools.magusbuddy.viewmodel.SacralMagicDatabaseViewModel;
import com.lazyfools.magusbuddy.HomeActivity;
import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;

import static com.lazyfools.magusbuddy.utility.Utility.join;

public class SacralMagicSingleFragment extends SingleFragment<SacralMagicDatabaseViewModel> {

    public SacralMagicSingleFragment() {
        super(SacralMagicDatabaseViewModel.class);
    }

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        ((HomeActivity)getActivity()).setBottomNavigationVisibility(View.GONE);

        _viewModel = ViewModelProviders.of(this).get(SacralMagicDatabaseViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneSacralMagicByID(id).observe(this, new Observer<SacralMagicEntity>() {
            @Override
            public void onChanged(@Nullable final SacralMagicEntity sacralMagic) {
                populateWith(sacralMagic);
            }
        });
    }

    private void populateWith(SacralMagicEntity entity) {
        ((TextView)getView().findViewById(R.id.title_value)).setText(entity.getName());

        setAndMakeVisible(getView(),
            join(',',
                entity.getType().toString(),
                entity.getSubType(),
                SacralMagicEntity.SphereEnum.toString(entity.getSphere())),
            R.id.type_value);

        populateWithStats(entity);

        ((TextView)getView().findViewById(R.id.description)).setText(
                spacingParagraphFrom(entity.getDescription())
        );

        entity.setDescTables(populateWithTables());
    }

    private void populateWithStats(SacralMagicEntity entity) {
        ((TextView) getView().findViewById(R.id.mp)).setText(getText(R.string.kp));
        ((TextView) getView().findViewById(R.id.mp_value)).setText(String.valueOf(entity.getKp()));

        //Set EKp text
        if (entity.getEkpText().isEmpty()) {
            ((TextView) getView().findViewById(R.id.emp)).setText(getText(R.string.ekp));
        }
        else {
            ((TextView) getView().findViewById(R.id.emp)).setText(String.format("Kp/%s", entity.getEkpText()));
        }

        setOrHide(getView(), entity.getEkp(), R.id.emp_value, R.id.emp_layout);

        TextView castTimeValue = getView().findViewById(R.id.casttime_value);
        castTimeValue.setText(entity.getCastTime());

        GridLayout propertiesLayout = getView().findViewById(R.id.properties_layout);
        setOrHide(propertiesLayout, entity.getCastTime(), R.id.casttime_value, R.id.casttime_value, R.id.casttime);
        setOrHide(propertiesLayout, entity.getRange(), R.id.range_value,  R.id.range_value, R.id.range);
        setOrHide(propertiesLayout, entity.getDurationTime(), R.id.durationtime_value, R.id.durationtime_value, R.id.durationtime);
        setAndMakeVisible(propertiesLayout, entity.getMagicResistance(), R.id.resistance_value, R.id.resistance_value, R.id.resistance);
    }
}
