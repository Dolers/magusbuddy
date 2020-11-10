package com.lazyfools.magusbuddy.page.codex.battlesituation;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lazyfools.magusbuddy.R;
import com.lazyfools.magusbuddy.database.entity.BattlesituationEntity;
import com.lazyfools.magusbuddy.page.codex.SingleFragment;
import com.lazyfools.magusbuddy.viewmodel.BattlesituationDatabaseViewModel;


public class BattlesituationSingleFragment extends SingleFragment<BattlesituationDatabaseViewModel> {
    public BattlesituationSingleFragment() {
        super(BattlesituationDatabaseViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.battlesituation_single_show, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Integer id = getArguments().getInt(getResources().getString(R.string.SKILL_ID));

        _viewModel.getOneBattlesituationByID(id).observe(this, new Observer<BattlesituationEntity>() {
            @Override
            public void onChanged(@Nullable final BattlesituationEntity Battlesituation) {
                populateWith(Battlesituation);
            }
        });
    }

    private void populateWith(BattlesituationEntity entity) {
        TextView titleTextView = getView().findViewById(R.id.title_value);
        titleTextView.setText(entity.getName());

        populateWithStats(entity);
        
        TextView descriptionTextView = getView().findViewById(R.id.description);
        descriptionTextView.setText(entity.getDescription());
    }

    private void populateWithStats(BattlesituationEntity entity) {
        TextView KeTextView = getView().findViewById(R.id.ke_value);
        KeTextView.setText(entity.getKe());
        
        TextView TeTextView = getView().findViewById(R.id.te_value);
        TeTextView.setText(entity.getTe());
        
        TextView VeTextView = getView().findViewById(R.id.ve_value);
        VeTextView.setText(entity.getVe());

        TextView CeTextView = getView().findViewById(R.id.ce_value);
        CeTextView.setText(entity.getCe());
    }
}
