package com.lazyfools.magusbuddy.page.codex.bardmagic;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.page.codex.SingleFragment;
import com.lazyfools.magusbuddy.viewmodel.BardMagicDatabaseViewModel;


public class BardMagicSingleFragment extends SingleFragment<BardMagicDatabaseViewModel> {

    public BardMagicSingleFragment() {
        super(BardMagicDatabaseViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneBardMagicByID(id).observe(this, new Observer<BardMagicEntity>() {
            @Override
            public void onChanged(@Nullable final BardMagicEntity bardMagic) {
                populateWith(bardMagic);
            }
        });
    }

    private void populateWith(BardMagicEntity entity)
    {
        ((TextView)getView().findViewById(R.id.title_value)).setText(entity.getName());

        populateWithStats(entity);

        ((TextView)getView().findViewById(R.id.description)).setText(
                spacingParagraphFrom(entity.getDescription())
        );
    }

    private void populateWithStats(BardMagicEntity entity) {
        ((TextView) getView().findViewById(R.id.mp_value)).setText(Integer.toString(entity.getMp()));

        setOrHide(getView(), entity.getEmp(), R.id.emp_value, R.id.emp_layout);

        setAndMakeVisible(getView(), entity.getType().toString(), R.id.type_value);

        GridLayout propertiesLayout = getView().findViewById(R.id.properties_layout);
        setOrHide(propertiesLayout, entity.getCastTime(), R.id.casttime_value, R.id.casttime_value, R.id.casttime);
        setOrHide(propertiesLayout, entity.getRange(), R.id.range_value,  R.id.range_value, R.id.range);
        setOrHide(propertiesLayout, entity.getDurationTime(), R.id.durationtime_value, R.id.durationtime_value, R.id.durationtime);
        setOrHide(propertiesLayout, "", R.id.resistance_value, R.id.resistance_value, R.id.resistance);
    }
}
