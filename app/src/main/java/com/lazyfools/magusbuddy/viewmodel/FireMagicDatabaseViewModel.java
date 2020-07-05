package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.FireMagicRepository;
import com.lazyfools.magusbuddy.database.entity.FireMagicEntity;
import com.lazyfools.magusbuddy.database.entity.FireMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class FireMagicDatabaseViewModel extends AndroidViewModel {
    private FireMagicRepository _repository;
    private LiveData<List<FireMagicEntity>> _allFireMagic = null;

    public FireMagicDatabaseViewModel(Application application) {
        super(application);
        _repository = new FireMagicRepository(application);
    }

    public LiveData<List<FireMagicEntity>> getAllFireMagic() {
        if (_allFireMagic == null){
            _allFireMagic = _repository.getAll();
        }
        return _allFireMagic;
    }

    public LiveData<List<NameEntity>> getAllFireMagicNamesOfType(FireMagicEntity.TypeEnum type) {
        return _repository.getAllFireMagicNamesOfType(type);
    }

    public LiveData<List<FireMagicType>> getAllFireMagicTypes() {
        return _repository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllFireMagicNames() {
        return _repository.getAllNames();
    }

    public LiveData<List<NameEntity>> getFireMagicNamesOfFilter(String name) {
        return _repository.getNamesOfFilter(name);
    }

    public LiveData<FireMagicEntity> getOneFireMagicByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<FireMagicEntity> getOneFireMagicByName(String name) {
        return _repository.getOneByName(name);
    }
}
