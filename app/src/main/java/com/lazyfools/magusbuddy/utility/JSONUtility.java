package com.lazyfools.magusbuddy.utility;

import com.lazyfools.magusbuddy.database.entity.WitchMagicEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtility {
    static public String parseStringWithDefault(JSONObject json, String value) throws JSONException {
        if (json.has(value)){
            return json.getString(value);
        }
        return "";
    }

    static public Integer parseIntegerWithDefault(JSONObject json, String value) throws JSONException {
        if (json.has(value)){
            return json.getInt(value);
        }
        return 0;
    }

    static public Integer parseIntegerWithDefault(JSONObject json, String value, Integer defaultValue) throws JSONException {
        if (json.has(value)){
            return json.getInt(value);
        }
        return defaultValue;
    }

    static public ArrayList<String> parseDescriptionTables(JSONObject qJson) throws JSONException {
        if (qJson.has("tablazatok")){
            ArrayList<String> listdata = new ArrayList<String>();
            JSONArray jArray = qJson.getJSONArray("tablazatok");
            for (int j=0;j<jArray.length();j++){
                listdata.add(jArray.getString(j));
            }
            return listdata;
        }
        return null;
    }
}
