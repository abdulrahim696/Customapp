package app.custom.com.customapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import app.custom.com.customapp.R;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity {

    private Realm realm;
    private RealmConfiguration context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);

      /*  Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("GalleryImages.realm")
                .build();

        Realm realm = Realm.getInstance(config);
//        realm.beginTransaction();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmPojoClass realmPojoClass = realm.createObject(RealmPojoClass.class);
                realmPojoClass.setText("kavya");
                realmPojoClass.setImage(1);

                RealmPojoClass realmPojoClass1 = realm.createObject(RealmPojoClass.class);
                realmPojoClass.setText("kavya");
                realmPojoClass.setImage(2);

                RealmPojoClass realmPojoClass2 = realm.createObject(RealmPojoClass.class);
                realmPojoClass.setText("Radha");
                realmPojoClass.setImage(3);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {

            }
        });
*/
        //        realm.commitTransaction();

/*
        //get the result
        RealmResults<RealmPojoClass> findall = realm.where(RealmPojoClass.class).findAll();
        if (findall.size() > 0) {
            for (RealmPojoClass c : findall) {
                Log.d("results1", c.getText());
                Log.d("results1", "" + c.getImage());

            }
        }
        realm.beginTransaction();
        for (RealmPojoClass value : findall) {
            if (value.getText().equals("abdul")) {
                Toast.makeText(this, "Found", Toast.LENGTH_SHORT).show();
                value.deleteFromRealm();
            }
        }
        realm.commitTransaction();

        //get the result
        RealmResults<RealmPojoClass> findall2 = realm.where(RealmPojoClass.class).findAll();
        if (findall.size() > 0) {
            for (RealmPojoClass c : findall2) {
                Log.d("results2", c.getText());
                Log.d("results2", "" + c.getImage());

            }


        }*/
    }
}
