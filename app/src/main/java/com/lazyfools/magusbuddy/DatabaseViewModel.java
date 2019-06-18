package com.lazyfools.magusbuddy;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.lazyfools.magusbuddy.database.CharacterEntity;
import com.lazyfools.magusbuddy.database.CharacterRepository;

import java.util.List;

public class DatabaseViewModel extends AndroidViewModel {
    private CharacterRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<CharacterEntity>> mAllCharacter;

    public DatabaseViewModel (Application application) {
        super(application);
        mRepository = new CharacterRepository(application);
        mAllCharacter = mRepository.getAllCharacter();
    }

    public LiveData<List<CharacterEntity>> getAllCharacter() { return mAllCharacter; }

}
