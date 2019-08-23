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
    }

    @Override
    public void populate() {
        for (Populizer p : _populizers) {
            p.populate();
        }
    }
}
