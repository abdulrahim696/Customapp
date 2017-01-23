package app.custom.com.customapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Ibrahim on 26-12-2016.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> implements View.OnClickListener, Communicator {

    Context con;

    ArrayList<GalleryPojoClass> galleryImages;
    ArrayList<Integer> checked_items = new ArrayList<>();


    public GalleryAdapter(Context con, ArrayList<GalleryPojoClass> galleryImages) {
        this.con = con;
        this.galleryImages = galleryImages;
        Log.i("khaliq", "GalleryAdapter: " + galleryImages.size());
    }

    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(con).inflate(R.layout.gallery, parent, false);
        return new GalleryHolder(view);
    }

    @Override
    public void onBindViewHolder(final GalleryHolder holder, final int position) {
        final GalleryPojoClass gallerypojo = galleryImages.get(position);
        //holder.nameTxt.setText(gallerypojo.getName());
        Glide.with(con).load(gallerypojo.getUri()).placeholder(R.drawable.ic_menu_gallery).into(holder.img);
        // Picasso.with(con).load(gallerypojo.getUri()).placeholder(R.drawable.ic_menu_share).into(holder.img);

       /*text = (TextView)timedis.setText((int) new Date().getTime());*/


       /* holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(con,"Checked item at position"+position,Toast.LENGTH_SHORT).show();
            }
        });*/


        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                     checked_items.add(position);
//                    galleryImages.remove(position);
//                    notifyDataSetChanged();
                    //Log.i(TAG, "onCheckedChanged: "+position);
                    holder.check.setChecked(b);
                    Toast.makeText(con, "Checked item at position" + position, Toast.LENGTH_SHORT).show();

                } else {
                    //checked_items.remove(position);
                    holder.check.setChecked(b);
                    Toast.makeText(con, "unChecked item at position" + position, Toast.LENGTH_SHORT).show();

                }

            }

        });


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(con, "Onclick called at position" + position, Toast.LENGTH_SHORT).show();

            }
        });
        holder.img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //checked_items.remove(position) ;
                galleryImages.remove(position);
                notifyDataSetChanged();
                //Toast.makeText(con,"OnLong called at position"+position,Toast.LENGTH_SHORT).show();
//              RecyclerView.LayoutManager.remove(position);


                return false;
            }


        });
    }


    @Override
    public int getItemCount() {
        return galleryImages.size();
    }


    @Override
    public void onClick(View view) {

    }
    //this is shit

    @Override
    public void sendMessage() {
        Toast.makeText(con, "The Method is been execute", Toast.LENGTH_SHORT);
    }

    public void selectedDelete(boolean delete) {
        if (delete) {
            for(int pos = 0; pos <= checked_items.size()-1; pos++) {
                //galleryImages.remove(checked_items.get(pos));
                galleryImages.remove(pos);
                notifyDataSetChanged();
            }
        }

    }
}
