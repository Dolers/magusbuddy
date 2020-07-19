package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.SacralMagicRepository;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicEntity;
import com.lazyfools.magusbuddy.database.entity.SacralMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class SacralMagicDatabaseViewModel extends AndroidViewModel {
    private SacralMagicRepository _repository;
    private LiveData<List<SacralMagicEntity>> _allSacralMagic = null;

    public SacralMagicDatabaseViewModel(Application application) {
        super(application);
        _repository = new SacralMagicRepository(application);
    }

    public LiveData<List<SacralMagicEntity>> getAllSacralMagic() {
        if (_allSacralMagic == null){
            _allSacralMagic = _repository.getAll();
        }
        return _allSacralMagic;
    }

    public LiveData<List<NameEntity>> getAllSacralMagicNamesOfType(SacralMagicEntity.TypeEnum type) {
        return _repository.getAllSacralMagicNamesOfType(type);
    }

    public LiveData<List<SacralMagicType>> getAllSacralMagicTypes() {
        return _repository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllSacralMagicNames() {
        return _repository.getAllNames();
    }

    public LiveData<SacralMagicEntity> getOneSacralMagicByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<SacralMagicEntity> getOneSacralMagicByName(String name) {
        return _repository.getOneByName(name);
    }
}
