package com.lazyfools.magusbuddy.dreonar;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.lazyfools.magusbuddy.database.APIKeyEntity;
import com.lazyfools.magusbuddy.database.DatabaseRepository;
import com.lazyfools.magusbuddy.utility.HTMLPageDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DreonarCharacterHandler {
    private LiveData<List<APIKeyEntity>> mAllKeys;
    private DatabaseRepository mRepository;

    public DreonarCharacterHandler(Application app) {
        mRepository = new DatabaseRepository(app);
        mAllKeys = mRepository.getAllKeys();
    }

    public void insertKey(String key,boolean mine){
        mRepository.insertAPIKey(key,mine);
        //TODO download only new API key characters
    }

    public void updateCharacters(){
        if (mRepository.hasCharacter()){
            mRepository.deleteAllCharacter();
        }
        for (final APIKeyEntity key : mAllKeys.getValue()){
            //Download character pages
            downloadCharacters(key.getId(),key.getHash());
        }
    }

    private void downloadCharacters(final int apiKeyID, final String apiKeyHash) {
        new HTMLPageDownloader(apiKeyHash,new HTMLPageDownloader.HTMLPageDownloaderListener(){

            @Override
            public void completionCallBack(String html) {
                Log.i("DreonarCharacterHandler", html);
                try {
                    JSONObject jsonObj = new JSONObject(html);
                    parseJSONAllCharacter(apiKeyID,(JSONArray)jsonObj.get("karakterek"));
                } catch (JSONException e) {
                    Log.e("DreonarCharacterHandler", "Couldn't parse JSON");
                    e.printStackTrace();
                }
            }
        });
    }


    private void parseJSONAllCharacter(int key, JSONArray characters) throws JSONException {
        if (characters == null) return;
        Log.d("parseJSONAllCharacter", Integer.toString(key));
        for (int i = 0; i< characters.length(); i++) {
            JSONObject character = (JSONObject)characters.get(i);
            mRepository.insertCharacter(key,
                                        Integer.valueOf(character.getString("karakter_id")),
                                        character.getString("nev"));
        }
    }
}
