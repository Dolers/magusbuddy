package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.repository.WarlockMagicRepository;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicEntity;
import com.lazyfools.magusbuddy.database.entity.WarlockMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class WarlockMagicDatabaseViewModel extends AndroidViewModel {
    private WarlockMagicRepository _repository;
    private LiveData<List<WarlockMagicEntity>> _allWarlockMagic = null;

    public WarlockMagicDatabaseViewModel(Application application) {
        super(application);
        _repository = new WarlockMagicRepository(application);
    }

    public LiveData<List<WarlockMagicEntity>> getAllWarlockMagic() {
        if (_allWarlockMagic == null){
            _allWarlockMagic = _repository.getAll();
        }
        return _allWarlockMagic;
    }

    public LiveData<List<NameEntity>> getAllWarlockMagicNamesOfType(WarlockMagicEntity.TypeEnum type) {
        return _repository.getAllWarlockMagicNamesOfType(type);
    }

    public LiveData<List<WarlockMagicType>> getAllWarlockMagicTypes() {
        return _repository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllWarlockMagicNames() {
        return _repository.getAllNames();
    }

    public LiveData<WarlockMagicEntity> getOneWarlockMagicByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<WarlockMagicEntity> getOneWarlockMagicByName(String name) {
        return _repository.getOneByName(name);
    }
}
