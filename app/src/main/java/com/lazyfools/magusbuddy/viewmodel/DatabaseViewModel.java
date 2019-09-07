package com.lazyfools.magusbuddy.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.CodexRepository;
import com.lazyfools.magusbuddy.database.QualificationRepository;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.CharacterRepository;
import com.lazyfools.magusbuddy.database.entity.CodexEntity;
import com.lazyfools.magusbuddy.database.entity.HighMagicType;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.NameEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class DatabaseViewModel extends AndroidViewModel {
    private CharacterRepository _characterRepository;
    private QualificationRepository _qualificationRepository;
    private CodexRepository _codexRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<CharacterEntity>> _allCharacters = null;
    private LiveData<List<QualificationEntity>> _allQualifications = null;

    public DatabaseViewModel (Application application) {
        super(application);
        _characterRepository = new CharacterRepository(application);
        _qualificationRepository = new QualificationRepository(application);
        _codexRepository = new CodexRepository(application);
    }

    public LiveData<List<CharacterEntity>> getAllCharacters() {
        if (_allCharacters == null){
            _allCharacters = _characterRepository.getAllCharacter();
        }
        return _allCharacters; }

    public LiveData<List<QualificationEntity>> getAllQualifications() {
        if (_allQualifications == null){
            _allQualifications = _qualificationRepository.getAll();
        }
        return _allQualifications;
    }

    public LiveData<List<NameEntity>> getAllQualificationNamesOfType(QualificationEntity.TypeEnum type) {
        return _qualificationRepository.getAllQualificationNamesOfType(type);
    }

    public LiveData<List<QualificationType>> getAllQualificationTypes() {
        return _qualificationRepository.getAllTypes();
    }

    public LiveData<List<NameEntity>> getAllQualificationNames() {
        return _qualificationRepository.getAllNames();
    }

    public LiveData<List<NameEntity>> getQualificationNamesOfFilter(String name) {
        return _qualificationRepository.getNamesOfFilter(name);
    }

    public LiveData<QualificationEntity> getOneQualificationByID(Integer id) {
        return _qualificationRepository.getOneByID(id);
    }

    public LiveData<QualificationEntity> getOneQualificationByName(String name) {
        return _qualificationRepository.getOneByName(name);
    }

    public LiveData<List<CodexEntity>> getAllCodex() {
        return _codexRepository.getLiveAll();
    }
}
