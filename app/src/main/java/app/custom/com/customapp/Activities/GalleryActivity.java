package app.custom.com.customapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;

import app.custom.com.customapp.ImagesDisplayAdapter;
import app.custom.com.customapp.R;
import app.custom.com.customapp.RealmPojoClass;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
//import static app.custom.com.customapp.R.id.imggg;


public class GalleryActivity extends AppCompatActivity {
    public Realm realm;
    ImageView imageView;
    private Toolbar toolbar;
    RecyclerView rev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        toolbar = (Toolbar) findViewById(R.id.toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("GalleryImages.realm")
                .build();

        realm = Realm.getInstance(config);

        RealmResults<RealmPojoClass> results = realm.where(RealmPojoClass.class).findAll();
        /*final RealmResults<RealmPojoClass> results = realm.where(RealmPojoClass.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Delete all matches
                results.deleteAllFromRealm();
            }
        });*/
//        Uri uri= Uri.parse();
       // Log.i("xxxxxx", "onCreate: " + results.size());
        //Glide.with(getApplicationContext()).load(results.get(0).getImagePath().toString()).placeholder(R.drawable.dark).into(imageView);
        ArrayList<RealmPojoClass> imgPaths = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            imgPaths.add(new RealmPojoClass(results.get(i).getImagePath().toString(), results.get(i).getTime()));

        }
        //Log.i("jjjjjj", "onCreate: " + imgPaths.size());

        rev = (RecyclerView) findViewById(R.id.rev2);
        rev.setLayoutManager(new GridLayoutManager(this, 3));
        rev.setAdapter(new ImagesDisplayAdapter(this, imgPaths));
        //realm.cancelTransaction();


        // Picasso.with(getApplicationContext()).load(uri).placeholder(R.drawable.dark).into(imageView);
//       imageView.setImageURI(uri);

        /*for(int i=0;i<results.size();i++){

            Log.i("muteeun", "onCreate: "+results.get(i));


        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }
}
