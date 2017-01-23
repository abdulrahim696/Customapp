package app.custom.com.customapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import app.custom.com.customapp.Communicator;
import app.custom.com.customapp.GalleryAdapter;
import app.custom.com.customapp.GalleryPojoClass;
import app.custom.com.customapp.R;
import app.custom.com.customapp.RealmPojoClass;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class  MultipleSelectImageActivity extends AppCompatActivity {
    private GalleryAdapter galleryAdapter;
    RecyclerView rv;
    ArrayList<String> fileroutes = new ArrayList<String>();
    ArrayList<Integer> checked_items= new ArrayList<>();
    private Toolbar toolbar;
    Communicator communicator;
    ArrayList<GalleryPojoClass> galleryPojoClassesList;
    public Realm realm;
    Button uploadbutton;
    String userName = "Abdul Rahim";
    String image="123";
    private  String url ="http://192.168.0.138:9000/dhc/services/rest/api/v1/image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiple_select_image_activty);
        Realm.init(this);
        final RealmConfiguration config = new RealmConfiguration.Builder()
                .name("GalleryImages.realm")
                .build();

        realm = Realm.getInstance(config);
       // communicator = (Communicator) new MultipleSelectImageActivity();
       // communicator.sendMessage();
        uploadbutton=(Button)findViewById(R.id.upload) ;
        uploadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm a");

                realm.beginTransaction();


                for(int i = 0; i<= galleryPojoClassesList.size()-1; i++){
                    RealmPojoClass obj = realm.createObject(RealmPojoClass.class);
                    obj.setImagePath(galleryPojoClassesList.get(i).getUri().getPath());
                    obj.setTime(simpleDateFormat.format(new Date()));

                }


                realm.commitTransaction();






               // String url = "http://httpbin.org/post";

                /*StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {




                                *//*try {
                                    JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                                    String site = jsonResponse.getString("site"),
                                            network = jsonResponse.getString("network");
                                    System.out.println("Site: "+site+"\nNetwork: "+network);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }*//*
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<>();
                        // the POST parameters:
                        params.put("name", "Abdul");
                        //params.put("Last", "Rahim");

                        return params;
                    }
                };
                Volley.newRequestQueue(MultipleSelectImageActivity.this).add(postRequest);*/




            }
        });

        toolbar = (Toolbar) findViewById(R.id.myappbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Gallery");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageButton addicon=(ImageButton) toolbar.findViewById(R.id.addicon);
        addicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //fileroutes.clear();
                FilePickerBuilder.getInstance().setMaxCount(10)
                        .setSelectedFiles(fileroutes)
                        .setActivityTheme(R.style.AppTheme)
                        .pickPhoto(MultipleSelectImageActivity.this);
                //Toast.makeText(getApplicationContext(),"clicked add button",Toast.LENGTH_SHORT).show();
            }
        });

        ImageView img = (ImageView) findViewById(R.id.multiple);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileroutes.clear();
                FilePickerBuilder.getInstance().setMaxCount(10)
                        .setSelectedFiles(fileroutes)

                        //.setActivityTheme(R.style.AppTheme)
                        .pickPhoto(MultipleSelectImageActivity.this);

            }
        });

        ImageButton del =(ImageButton)toolbar.findViewById(R.id.deleteicon);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (galleryAdapter == null) {
                    Toast.makeText(getApplicationContext(),"Please Select the items before you Delete",Toast.LENGTH_SHORT).show();
                }
                else{
                    galleryAdapter.selectedDelete(true);
                    Toast.makeText(getApplicationContext(),"clicked Delete button",Toast.LENGTH_SHORT).show();
                }
                /*SparseBooleanArray checked_items = getListView().getCheckedItemPositions();
                int itemCount = getListView().getCount();

                for(int i=itemCount-1; i >= 0; i--){
                    if(checked_items.get(i)){
                        adapter.remove(list.get(i));
                    }
                }
                checkedItemPositions.clear();
                adapter.notifyDataSetChanged();*/

            }
        });



        rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new GridLayoutManager(this,3));



        }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       /* Uri   uri=data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            String encodedImageData =getEncoded64ImageStringFromBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }



        public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

         imgString;
*/



        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE:
                if (resultCode == RESULT_OK && data != null) {
                    fileroutes = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS);
                    GalleryPojoClass s;
                    galleryPojoClassesList = new ArrayList<>();

                    try {
                        for (String path : fileroutes) {
                            s = new GalleryPojoClass();
                            //s.setName(path.substring(path.lastIndexOf("/") + 1));
                            s.setUri(Uri.fromFile(new File(path)));
                            galleryPojoClassesList.add(s);


                          /* */
                        }


                        galleryAdapter = new GalleryAdapter(this, galleryPojoClassesList);

                        rv.setAdapter(galleryAdapter);



                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {

           // realm.cancelTransaction();
            finish();
//
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        realm.cancelTransaction();
        /*Intent i = new Intent(getApplicationContext(), WelcomActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/
        finish();
//        startActivity(i);


    }







}
