package com.project.android.democrudrealm.module.helper;

import android.content.Context;
import android.util.Log;

import com.project.android.democrudrealm.module.model.Data;
import com.project.android.democrudrealm.module.model.DataModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by agung on 19/09/18.
 */

public class RealmHelper {
    private static final String TAG = "RealmHelper";
    private Realm realm;
    private RealmResults<Data> realmResults;

    //Constructor to create instance realm

    public RealmHelper(Context context) {
        realm = Realm.getInstance(Realm.getDefaultConfiguration());
    }

    private void showLog(String show) {
        Log.d(TAG, show);
    }

    //add data
    public void add (String title, String description){
        Data book = new Data();
        book.setId((int)(System.currentTimeMillis()/1000));
        book.setTitle(title);
        book.setDescription(description);

        realm.beginTransaction();
        realm.copyToRealm(book);
        realm.commitTransaction();

        showLog("Added : " + title);
        showLog("Desc : " + description);
    }

    //findAll Data
    public ArrayList<DataModel> findAll(){
        ArrayList<DataModel> dataModels = new ArrayList<>();

        realmResults = realm.where(Data.class).findAll();
        realmResults.sort("id", Sort.DESCENDING);
        showLog("Title: " + String.valueOf(realmResults.get(0).getTitle()));
        showLog("Desc: " + String.valueOf(realmResults.get(0).getDescription()));

        for (int i = 0; i < realmResults.size(); i++){
            String title, description;
            int id = realmResults.get(i).getId();
            title = realmResults.get(i).getTitle();
            description = realmResults.get(i).getDescription();
            dataModels.add(new DataModel(id, title, description));
        }
        return dataModels;
    }

}
