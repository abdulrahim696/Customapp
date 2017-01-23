package app.custom.com.customapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Ibrahim on 26-12-2016.
 */
public class GalleryHolder  extends RecyclerView.ViewHolder{
    //TextView timedis;
    ImageView img;
    CheckBox check;





    public GalleryHolder(View itemView) {
        super(itemView);

        /*timedis = (TextView)itemView.findViewById(R.id.time);
        timedis.setText((int) new Date().getTime());*/
        img = (ImageView)itemView.findViewById(R.id.gallerimg1);
        check= (CheckBox)itemView.findViewById(R.id.chkbox);

    }
   /* public void bindView(String string) {
        timedis.setText((int) new Date().getTime());
        //text = (TextView)timedis.setText((int))
    }*/
}
