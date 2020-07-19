package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.CharacterRepository;
import com.lazyfools.magusbuddy.database.HighMagicRepository;
import com.lazyfools.magusbuddy.database.HighMagicRepository;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicType;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class HighMagicDatabaseViewModel extends AndroidViewModel {
    private HighMagicRepository _repository;
    private LiveData<List<HighMagicEntity>> _allHighMagic = null;

    public HighMagicDatabaseViewModel(Application application) {
        super(application);
        _repository = new HighMagicRepository(application);
    }

    public LiveData<List<HighMagicEntity>> getAllHighMagic() {
        if (_allHighMagic == null){
            _allHighMagic = _repository.getAll();
        }
        return _allHighMagic;
    }

    public LiveData<List<NameEntity>> getAllHighMagicNamesOfType(HighMagicEntity.TypeEnum type) {
        return _repository.getAllHighMagicNamesOfType(type);
    }

    public LiveData<List<HighMagicType>> getAllHighMagicTypes() {
        return _repository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllHighMagicNames() {
        return _repository.getAllNames();
    }

    public LiveData<HighMagicEntity> getOneHighMagicByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<HighMagicEntity> getOneHighMagicByName(String name) {
        return _repository.getOneByName(name);
    }
}
