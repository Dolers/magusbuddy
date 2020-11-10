package com.lazyfools.magusbuddy.page.codex.pszimagic;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.page.codex.SingleFragment;
import com.lazyfools.magusbuddy.viewmodel.PsziMagicDatabaseViewModel;

import static com.lazyfools.magusbuddy.utility.Utility.join;

public class PsziMagicSingleFragment extends SingleFragment<PsziMagicDatabaseViewModel> {

    public PsziMagicSingleFragment() {
        super(PsziMagicDatabaseViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOnePsziMagicByID(id).observe(this, new Observer<PsziMagicEntity>() {
            @Override
            public void onChanged(@Nullable final PsziMagicEntity psziMagic) {
                populateWith(psziMagic);
            }
        });
    }

    private void populateWith(PsziMagicEntity entity)
    {
        ((TextView)getView().findViewById(R.id.title_value)).setText(entity.getName());

        populateWithStats(entity);

        ((TextView)getView().findViewById(R.id.description)).setText(
                spacingParagraphFrom(entity.getDescription())
        );

        entity.setDescTables(populateWithTables());

        ((TextView)getView().findViewById(R.id.special)).setText(entity.getSpecial());
    }

    private void populateWithStats(PsziMagicEntity entity) {
        ((TextView) getView().findViewById(R.id.mp)).setText(getText(R.string.pszipont));
        ((TextView) getView().findViewById(R.id.mp_value)).setText(String.valueOf(entity.getMp()));

        //Set Emp text
        TextView ekp = getView().findViewById(R.id.emp);
        if (entity.getEmpText().isEmpty())
            ekp.setText(getText(R.string.pszipont));
        else
            ekp.setText(String.format("%s/%s", getText(R.string.pszipont), entity.getEmpText()));

        setOrHide(getView(), entity.getEmp(), R.id.emp_value, R.id.emp_layout);

        setAndMakeVisible(getView(), join(',', entity.getType().toString(), entity.getSubType()), R.id.type_value);

        GridLayout propertiesLayout = getView().findViewById(R.id.properties_layout);
        setOrHide(propertiesLayout, entity.getCastTime(), R.id.casttime_value, R.id.casttime_value, R.id.casttime);
        setOrHide(propertiesLayout, getString(R.string.default_range), R.id.range_value,  R.id.range_value, R.id.range);
        setOrHide(propertiesLayout, entity.getDurationTime(), R.id.durationtime_value, R.id.durationtime_value, R.id.durationtime);
        setOrHide(propertiesLayout, entity.getMagicResistance(), R.id.resistance_value, R.id.resistance_value, R.id.resistance);
    }
}
