package com.lazyfools.magusbuddy;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.QualificationRepository;
import com.lazyfools.magusbuddy.database.entity.CharacterEntity;
import com.lazyfools.magusbuddy.database.CharacterRepository;
import com.lazyfools.magusbuddy.database.entity.QualificationEntity;
import com.lazyfools.magusbuddy.database.entity.QualificationType;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {
    private CharacterRepository mCharacterRepository;
    private QualificationRepository mQualificationRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<CharacterEntity>> mAllCharacters = null;
    private LiveData<List<QualificationEntity>> mAllQualifications = null;

    public DatabaseViewModel (Application application) {
        super(application);
        mCharacterRepository = new CharacterRepository(application);
        mQualificationRepository = new QualificationRepository(application);
    }

    public LiveData<List<CharacterEntity>> getAllCharacters() {
        if (mAllCharacters == null){
            mAllCharacters = mCharacterRepository.getAllCharacter();
        }
        return mAllCharacters; }

    public LiveData<List<QualificationEntity>> getAllQualifications() {
        if (mAllQualifications == null){
            mAllQualifications = mQualificationRepository.getAllQualifications();
        }
        return mAllQualifications;
    }
    public LiveData<List<QualificationEntity>> getAllQualificationsOfType(QualificationEntity.QualificationTypeEnum type) {
        return mQualificationRepository.getAllQualificationsOfType(type);
    }

    public LiveData<List<QualificationType>> getAllQualificationTypes() {
        return mQualificationRepository.getAllTypes();
    }
}
