package com.lazyfools.magusbuddy.page.codex.warlockmagic;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.page.codex.SingleFragment;
import com.lazyfools.magusbuddy.viewmodel.WarlockMagicDatabaseViewModel;

import static com.lazyfools.magusbuddy.utility.Utility.join;

public class WarlockMagicSingleFragment extends SingleFragment<WarlockMagicDatabaseViewModel> {

    public WarlockMagicSingleFragment() {
        super(WarlockMagicDatabaseViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneWarlockMagicByID(id).observe(this, new Observer<WarlockMagicEntity>() {
            @Override
            public void onChanged(@Nullable final WarlockMagicEntity warlockMagic) {
                populateWith(warlockMagic);
            }
        });
    }

    private void populateWith(WarlockMagicEntity entity)
    {
        ((TextView)getView().findViewById(R.id.title_value)).setText(entity.getName());

        populateWithStats(entity);

        ((TextView)getView().findViewById(R.id.description)).setText(
                spacingParagraphFrom(entity.getDescription())
        );

        entity.setDescTables(populateWithTables());

        ((TextView)getView().findViewById(R.id.special)).setText(entity.getSpecial());
    }

    private void populateWithStats(WarlockMagicEntity entity) {
        ((TextView) getView().findViewById(R.id.mp_value)).setText(Integer.toString(entity.getMp()));

        if(entity.getBasice() > 1 && entity.getEmp() == 0){
            ((TextView) getView().findViewById(R.id.emp_value)).setText(Integer.toString(entity.getBasice()));
            ((TextView) getView().findViewById(R.id.emp)).setText("E");
        }
        else{
            setOrHide(getView(), entity.getEmp(), R.id.emp_value, R.id.emp_layout);
        }

        setAndMakeVisible(getView(), join(',', entity.getType().toString(), entity.getSubType().toString()), R.id.type_value);

        GridLayout propertiesLayout = getView().findViewById(R.id.properties_layout);
        setOrHide(propertiesLayout, entity.getCastTime(), R.id.casttime_value, R.id.casttime_value, R.id.casttime);
        setOrHide(propertiesLayout, entity.getRange(), R.id.range_value,  R.id.range_value, R.id.range);
        setOrHide(propertiesLayout, entity.getDurationTime(), R.id.durationtime_value, R.id.durationtime_value, R.id.durationtime);
        setOrHide(propertiesLayout, entity.getMagicResistance(), R.id.resistance_value, R.id.resistance_value, R.id.resistance);
    }
}
