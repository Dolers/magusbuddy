package com.lazyfools.magusbuddy;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.QualificationRepository;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.CharacterRepository;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationName;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

//TODO split viewmodel into smaller viewmodels
public class DatabaseViewModel extends AndroidViewModel {
    private CharacterRepository _characterRepository;
    private QualificationRepository _qualificationRepository;
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
    }

    public LiveData<List<CharacterEntity>> getAllCharacters() {
        if (_allCharacters == null){
            _allCharacters = _characterRepository.getAllCharacter();
        }
        return _allCharacters; }

    public LiveData<List<QualificationEntity>> getAllQualifications() {
        if (_allQualifications == null){
            _allQualifications = _qualificationRepository.getAllQualifications();
        }
        return _allQualifications;
    }

    public LiveData<List<QualificationName>> getAllQualificationNamesOfType(QualificationEntity.QualificationTypeEnum type) {
        return _qualificationRepository.getAllQualificationNamesOfType(type);
    }

    public LiveData<List<QualificationType>> getAllQualificationTypes() {
        return _qualificationRepository.getAllTypes();
    }

    public LiveData<List<QualificationName>> getAllQualificationNames() {
        return _qualificationRepository.getAllNames();
    }

    public LiveData<List<QualificationName>> getQualificationNamesOfFilter(String name) {
        return _qualificationRepository.getNamesOfFilter(name);
    }

    public LiveData<QualificationEntity> getOneQualificationByID(Integer id) {
        return _qualificationRepository.getOneByID(id);
    }

    public LiveData<QualificationEntity> getOneQualificationByName(String name) {
        return _qualificationRepository.getOneByName(name);
    }
}
