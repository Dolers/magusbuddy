package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.entity.BattlesituationEntity;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.repository.BattlesituationRepository;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class BattlesituationDatabaseViewModel extends AndroidViewModel {
    private BattlesituationRepository _repository;
    private LiveData<List<BattlesituationEntity>> _allBattlesituation = null;

    public BattlesituationDatabaseViewModel(Application application) {
        super(application);
        _repository = new BattlesituationRepository(application);
    }

    public LiveData<List<BattlesituationEntity>> getAllBattlesituation() {
        if (_allBattlesituation == null){
            _allBattlesituation = _repository.getAll();
        }
        return _allBattlesituation;
    }

    public LiveData<List<NameEntity>> getAllBattlesituationNames() {
        return _repository.getAllNames();
    }

    public LiveData<BattlesituationEntity> getOneBattlesituationByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<BattlesituationEntity> getOneBattlesituationByName(String name) {
        return _repository.getOneByName(name);
    }
}
