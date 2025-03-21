package com.lazyfools.magusbuddy.database.populate;

import android.content.Context;
import com.lazyfools.magusbuddy.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbPopulizer implements Populizer{
    private List<Populizer> _populizers = new ArrayList<>();
    public DbPopulizer(final Context context, AppDatabase db){
        _populizers.add(new CharacterPopulizer(db));//TODO Shouldn't be in the final product
        _populizers.add(new QualificationPopulizer(context,db));
        _populizers.add(new BattlesituationPopulizer(context,db));
        _populizers.add(new CodexPopulizer(context,db));
        _populizers.add(new HighMagicPopulizer(context,db));
        _populizers.add(new SacralMagicPopulizer(context,db));
        _populizers.add(new BardMagicPopulizer(context,db));
        _populizers.add(new PsziMagicPopulizer(context,db));
        _populizers.add(new FireMagicPopulizer(context,db));
        _populizers.add(new WitchMagicPopulizer(context,db));
        _populizers.add(new WarlockMagicPopulizer(context,db));
    }

    @Override
    public void populate() {
        for (Populizer p : _populizers) {
            p.populate();
        }
    }
}
