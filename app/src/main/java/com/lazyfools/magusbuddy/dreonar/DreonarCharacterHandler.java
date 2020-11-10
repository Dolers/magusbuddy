package com.lazyfools.magusbuddy.dreonar;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.lazyfools.magusbuddy.database.entity.APIKeyEntity;
import com.lazyfools.magusbuddy.database.repository.CharacterRepository;
import com.lazyfools.magusbuddy.utility.HTMLPageDownloader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DreonarCharacterHandler {
    private LiveData<List<APIKeyEntity>> _allKeys;
    private CharacterRepository _repository;

    public DreonarCharacterHandler(Application app) {
        _repository = new CharacterRepository(app);
        _allKeys = _repository.getAllKeys();
    }

    public void insertKey(String key,boolean mine){
        _repository.insertAPIKey(key,mine);
        //TODO download only new API key characters
    }

    public void updateCharacters(){
        if (_repository.hasCharacter()){
            _repository.deleteAllCharacter();
        }
        for (final APIKeyEntity key : _allKeys.getValue()){
            //Download character pages
            downloadCharacters(key.getId(),key.getHash());
        }
    }

    /*
    osszes karakter összefoglaló: http://dreonar.hu/index.php?pf=api&data=allCharacters&api_key=YOUR_KEY_HERE&callback=dreonar
    dreonar({
       "response_code":0,
       "response_text":"",
       "karakterek":[
          {
             "karakter_id":"35739",
             "nev":"Quince",
             "karakter_csoport_nev":"Kantesok",
             "faj_nev":"Ember",
             "kaszt_nev":"B\u00e1rd",
             "alkaszt_nev":"V\u00e1ndorl\u00f3 dalnok",
             "szint":"5",
             "szulofold":null,
             "jellem":null
          },
          ...
       ]
    })

    egy karakter: http://dreonar.hu/index.php?pf=api&data=character&karakter_id=1840&api_key=YOUR_KEY_HERE&callback=dreonar
    dreonar({
       "response_code":0,
       "response_text":"",
       "karakter":{
          "tulajdonsag":{
             "Er\u0151":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":13,
                "modositott_leiras":"13 (Eredeti)"
             },
             "Gyorsas\u00e1g":{
                "alap":18,
                "alap_leiras":"18 (Eredeti)",
                "modositott":17,
                "modositott_leiras":"18 (Eredeti) - 1 (P\u00e1nc\u00e9l)"
             },
             "\u00dcgyess\u00e9g":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":12,
                "modositott_leiras":"13 (Eredeti) - 1 (P\u00e1nc\u00e9l)"
             },
             "\u00c1ll\u00f3k\u00e9pess\u00e9g":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":12,
                "modositott_leiras":"13 (Eredeti) - 1 (P\u00e1nc\u00e9l)"
             },
             "Eg\u00e9szs\u00e9g":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":13,
                "modositott_leiras":"13 (Eredeti)"
             },
             "Karizma":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":13,
                "modositott_leiras":"13 (Eredeti)"
             },
             "Intelligencia":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":13,
                "modositott_leiras":"13 (Eredeti)"
             },
             "Akarater\u0151":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":13,
                "modositott_leiras":"13 (Eredeti)"
             },
             "Asztr\u00e1l":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":13,
                "modositott_leiras":"13 (Eredeti)"
             },
             "\u00c9rz\u00e9kel\u00e9s":{
                "alap":13,
                "alap_leiras":"13 (Eredeti)",
                "modositott":13,
                "modositott_leiras":"13 (Eredeti)"
             }
          },
          "kap":{
             "szintenkent":44,
             "aktualis":88,
             "elkoltott":{
                "osszesen":0,
                "ke":0,
                "te":0,
                "ve":0,
                "ce":0,
                "kepzettseg":0,
                "fp":0,
                "pszi":0,
                "kegy":0,
                "mana":0,
                "tulajdonsag":0
             }
          },
          "hatter":[
             {
                "hatter_id":0,
                "nev":"Alkaszt",
                "ingyenes":0,
                "pont":6
             },
             {
                "hatter_id":0,
                "nev":"Faj",
                "ingyenes":0,
                "pont":0
             },
             {
                "hatter_id":170,
                "nev":"M\u00e1gikus fog\u00e9konys\u00e1g",
                "ingyenes":1,
                "pont":1
             }
          ],
          "eletero":{
             "ep_max":13,
             "ep_akt":13,
             "fp_max":25,
             "fp_max_keplet":"12 (\u00c1ll\u00f3k\u00e9pess\u00e9g) + 13 (Akarater\u0151) + 0 (KAP) + 0 (K\u00e9pzetts\u00e9g)",
             "fp_akt":25
          },
          "nev":"dvsdf",
          "kaszt_nev":"B\u00e1rd",
          "alkaszt_nev":"Aszisz \u00e9nekmond\u00f3",
          "faj_nev":"Ember",
          "eletkor":0,
          "jellem":null,
          "vallas":null,
          "szulofold":null,
          "iskola":null,
          "tp_aktualis":101,
          "tp_kov_szint":301,
          "tp_kov_szinthez":200,
          "szint":2,
          "pszi":{
             "max":13,
             "max_keplet":"13 (Intelligencia) + 0 (KAP) + 0 (K\u00e9pzetts\u00e9g)",
             "intelligenciabol":13,
             "kapbol":0,
             "kepzettsegbol":0,
             "akt":13
          },
          "me":{
             "tudatalatti":{
                "asztral":"13",
                "mental":"13"
             },
             "hatter":0,
             "hatter_keplet":"0 (M\u00e1giatagad\u00e1s) + 0 (Szellemek j\u00f3indulata)",
             "hit":0,
             "statikus":{
                "asztral":0,
                "mental":0
             },
             "dinamikus":{
                "asztral":0,
                "mental":0
             },
             "magikus":{
                "asztral":0,
                "mental":0
             },
             "osszesen":{
                "asztral":13,
                "mental":13
             }
          },
          "kegy":{
             "max":0,
             "max_keplet":"0 (Vall\u00e1sismeret) + 0 (KAP) + 0 (Szerepj\u00e1t\u00e9kb\u00f3l)",
             "vallasismeretbol":0,
             "szerepjatekbol":0,
             "kapbol":0,
             "akt":0
          },
          "mana":{
             "max":2,
             "max_keplet":"2 (Tapasztalati m\u00e1gia) + 0 (Magasm\u00e1gia) + 0 (KAP)",
             "tapasztalati_magiabol":2,
             "magas_magiabol":0,
             "kapbol":0,
             "akt":2
          },
          "penz":{
             "arany":0,
             "ezust":0,
             "rez":0
          },
          "harcertek":{
             "ke":30,
             "ke_keplet":"17 (Gyorsas\u00e1g) + 13 (\u00c9rz\u00e9kel\u00e9s) + 0 (Hm) + 0 (Harct\u00e9ri gyakorlat)",
             "te":42,
             "te_keplet":"13 (Er\u0151) + 17 (Gyorsas\u00e1g) + 12 (\u00dcgyess\u00e9g) + 0 (Hm) + 0 (Harct\u00e9ri gyakorlat)",
             "ve":89,
             "ve_keplet":"60 (Alap) + 17 (Gyorsas\u00e1g) + 12 (\u00dcgyess\u00e9g) + 0 (Hm) + 0 (Harct\u00e9ri gyakorlat)",
             "ce":25,
             "ce_keplet":"12 (\u00dcgyess\u00e9g) + 13 (\u00c9rz\u00e9kel\u00e9s) + 0 (Hm) + 0 (Harct\u00e9ri gyakorlat) + 0 (Faj)"
          },
          "fegyver":{
             "vasarolt_hm":0,
             "hacteri_gyakbol_osszesen":0,
             "hacteri_gyakbol_elkoltve":0
          },
          "kepzettsegek":[
             {
                "nev":"Akrobatika",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":" (+2 V\u00c9 bizonyos helyzetekben) ",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"10%",
                "fok_bonusz":13
             },
             {
                "nev":"Csapdakeres\u00e9s",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"10%",
                "fok_bonusz":13
             },
             {
                "nev":"\u00c9rt\u00e9kbecsl\u00e9s",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"1. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Fegyverhaszn\u00e1lat",
                "spec":" (Dob\u00f3fegyverek) ",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"2. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Fegyverhaszn\u00e1lat",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"2. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Helyismeret",
                "spec":" (saj\u00e1t) ",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"3. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Kocsmai vereked\u00e9s",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"2. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Kult\u00fara",
                "spec":" (saj\u00e1t) ",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"3. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Legendaismeret",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"1. fok",
                "fok_bonusz":0
             },
             {
                "nev":"L\u00e9lektan",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"1. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Lop\u00f3dz\u00e1s",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"10%",
                "fok_bonusz":13
             },
             {
                "nev":"M\u0171v\u00e9szet",
                "spec":" (\u00c9nekl\u00e9s) ",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"1. fok",
                "fok_bonusz":0
             },
             {
                "nev":"M\u0171v\u00e9szet",
                "spec":" (Zen\u00e9l\u00e9s) ",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"1. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Nyelvtud\u00e1s",
                "spec":" (saj\u00e1t) ",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"3. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Rejtekhely kutat\u00e1s",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"10%",
                "fok_bonusz":13
             },
             {
                "nev":"Rejt\u0151zk\u00f6d\u00e9s",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"10%",
                "fok_bonusz":13
             },
             {
                "nev":"Szerencsej\u00e1t\u00e9k",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"2. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Szexu\u00e1lis kult\u00fara",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"2. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Tapasztalati m\u00e1gia",
                "spec":" (b\u00e1rd) ",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"3. fok",
                "fok_bonusz":0
             },
             {
                "nev":"V\u00e9rtviselet",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":10,
                "fok":"3. fok",
                "fok_bonusz":0
             },
             {
                "nev":"Zsebmetsz\u00e9s",
                "spec":"",
                "tul_bonusz":"",
                "akrobatika_bonusz":"",
                "elkoltott_kap":0,
                "elkoltott_kp":0,
                "fok":"10%",
                "fok_bonusz":13
             }
          ],
          "pancel":{
             "0":{
                "hasznal":1,
                "nev":"Brigandin"
             },
             "hasznalt":{
                "nev":"Brigandin",
                "tipus":"Neh\u00e9zv\u00e9rt",
                "suly":15,
                "mgt_keplet":"2(P\u00e1nc\u00e9l) - 1 (V\u00e9rtviselet)",
                "mgt":1,
                "stp":60,
                "sfe_max_keplet":"3 (P\u00e1nc\u00e9l) + 1 (V\u00e9rtviselet)",
                "sfe_max":4,
                "ar":4000,
                "sfe_akt":4,
                "fok":3
             }
          },
          "targy":{
             "ossz_suly":0,
             "ossz_mgt":0,
             "ossz_ertek":0
          },
          "jegyzet":null
       }
    })

    Ha sikertelen a lekérdezés:
    {
       "response_code":1,
       "response_text":"API hozz\u00e1f\u00e9r\u00e9s sikertelen. A megadott API kulcs nem megfelel\u0151."
    }
    */
    private void downloadCharacters(final int apiKeyID, final String apiKeyHash) {
        String URL = new StringBuilder("http://dreonar.hu/index.php?pf=api&data=allCharacters&api_key=").append(apiKeyHash).append("&callback=dreonar").toString();
        new HTMLPageDownloader(URL,new HTMLPageDownloader.HTMLPageDownloaderListener(){

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
        //TODO: download and store all characters with the karakter_ids received in the previous JSON

    }


    private void parseJSONAllCharacter(int key, JSONArray characters) throws JSONException {
        if (characters == null) return;
        Log.d("parseJSONAllCharacter", String.valueOf(key));
        for (int i = 0; i< characters.length(); i++) {
            JSONObject character = (JSONObject)characters.get(i);
            _repository.insertCharacter(key,
                                        Integer.valueOf(character.getString("karakter_id")),
                                        character.getString("nev"));
        }
    }
}
