package app.custom.com.customapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ibrahim on 05-01-2017.
 */

/*public class ImagesDisplayAdapter {
}*/
public class ImagesDisplayAdapter extends RecyclerView.Adapter<ImagesDisplayHolder>  {

    Context con;


    ArrayList<RealmPojoClass> imageDisplaypojos;


    public ImagesDisplayAdapter(Context con, ArrayList<RealmPojoClass> imageDisplaypojos){
        this.con=con;
        this.imageDisplaypojos=imageDisplaypojos;
        Log.i("khaliq", "GalleryAdapter: "+imageDisplaypojos.size());
    }

    @Override
    public ImagesDisplayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.imagedisplay,parent,false);
        return new ImagesDisplayHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesDisplayHolder holder, final int position) {
        Glide.with(con).load(imageDisplaypojos.get(position).getImagePath()).placeholder(R.drawable.ic_face_white_24dp).into(holder.imgs);
        //holder.nameTxt.setText(ga.getT());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        Date d=new Date();
        Log.i("ibbu", "onBindViewHolder: "+simpleDateFormat.format(d));

        holder.imgtime.setText(imageDisplaypojos.get(position).getTime());


        holder.imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(con,"Onclick called at position"+position,Toast.LENGTH_SHORT).show();



            }


        });
        holder.imgs.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Toast.makeText(con,"OnLong called at position"+position,Toast.LENGTH_SHORT).show();

                return false;
            }


        });
    }

    private void add(int position) {
    }




    @Override
    public int getItemCount() {
        return imageDisplaypojos.size();
    }




    }


