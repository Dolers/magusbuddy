package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.repository.WitchMagicRepository;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WitchMagicType;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class WitchMagicDatabaseViewModel extends AndroidViewModel {
    private WitchMagicRepository _repository;
    private LiveData<List<WitchMagicEntity>> _allWitchMagic = null;

    public WitchMagicDatabaseViewModel(Application application) {
        super(application);
        _repository = new WitchMagicRepository(application);
    }

    public LiveData<List<WitchMagicEntity>> getAllWitchMagic() {
        if (_allWitchMagic == null){
            _allWitchMagic = _repository.getAll();
        }
        return _allWitchMagic;
    }

    public LiveData<List<NameEntity>> getAllWitchMagicNamesOfType(WitchMagicEntity.TypeEnum type) {
        return _repository.getAllWitchMagicNamesOfType(type);
    }

    public LiveData<List<WitchMagicType>> getAllWitchMagicTypes() {
        return _repository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllWitchMagicNames() {
        return _repository.getAllNames();
    }

    public LiveData<WitchMagicEntity> getOneWitchMagicByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<WitchMagicEntity> getOneWitchMagicByName(String name) {
        return _repository.getOneByName(name);
    }
}
