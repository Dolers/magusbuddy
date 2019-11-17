package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.PsziMagicRepository;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicEntity;
import com.lazyfools.magusbuddy.database.entity.PsziMagicType;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class PsziMagicDatabaseViewModel extends AndroidViewModel {
    private PsziMagicRepository _repository;
    private LiveData<List<PsziMagicEntity>> _allPsziMagic = null;

    public PsziMagicDatabaseViewModel(Application application) {
        super(application);
        _repository = new PsziMagicRepository(application);
    }

    public LiveData<List<PsziMagicEntity>> getAllPsziMagic() {
        if (_allPsziMagic == null){
            _allPsziMagic = _repository.getAll();
        }
        return _allPsziMagic;
    }

    public LiveData<List<NameEntity>> getAllPsziMagicNamesOfType(PsziMagicEntity.TypeEnum type) {
        return _repository.getAllPsziMagicNamesOfType(type);
    }

    public LiveData<List<PsziMagicType>> getAllPsziMagicTypes() {
        return _repository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllPsziMagicNames() {
        return _repository.getAllNames();
    }

    public LiveData<List<NameEntity>> getPsziMagicNamesOfFilter(String name) {
        return _repository.getNamesOfFilter(name);
    }

    public LiveData<PsziMagicEntity> getOnePsziMagicByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<PsziMagicEntity> getOnePsziMagicByName(String name) {
        return _repository.getOneByName(name);
    }
}
