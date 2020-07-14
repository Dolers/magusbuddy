package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.SparseArray;

import com.lazyfools.magusbuddy.database.BardMagicRepository;
import com.lazyfools.magusbuddy.database.entity.BardMagicEntity;
import com.lazyfools.magusbuddy.database.entity.BardMagicType;
import com.lazyfools.magusbuddy.database.entity.NameEntity;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class BardMagicDatabaseViewModel extends AndroidViewModel {
    private BardMagicRepository _repository;
    private LiveData<List<BardMagicEntity>> _allBardMagic = null;

    public BardMagicDatabaseViewModel(Application application) {
        super(application);
        _repository = new BardMagicRepository(application);
    }

    public LiveData<List<BardMagicEntity>> getAllBardMagic() {
        if (_allBardMagic == null){
            _allBardMagic = _repository.getAll();
        }
        return _allBardMagic;
    }

    public LiveData<List<NameEntity>> getAllBardMagicNamesOfType(BardMagicEntity.TypeEnum type) {
        return _repository.getAllBardMagicNamesOfType(type);
    }

    public LiveData<List<BardMagicType>> getAllBardMagicTypes() {
        return _repository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllBardMagicNames() {
        return _repository.getAllNames();
    }

    public LiveData<List<NameEntity>> getBardMagicNamesOfFilter(String name) {
        return _repository.getNamesOfFilter(name);
    }

    public LiveData<BardMagicEntity> getOneBardMagicByID(Integer id) {
        return _repository.getOneByID(id);
    }

    public LiveData<BardMagicEntity> getOneBardMagicByName(String name) {
        return _repository.getOneByName(name);
    }
}
