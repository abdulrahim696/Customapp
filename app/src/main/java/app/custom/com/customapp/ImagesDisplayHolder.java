package app.custom.com.customapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by Ibrahim on 05-01-2017.
 */

public class ImagesDisplayHolder extends RecyclerView.ViewHolder {
    ImageView imgs;
    TextView imgtime;
    public ImagesDisplayHolder(View itemView) {
        super(itemView);
        imgs = (ImageView)itemView.findViewById(R.id.imgdisplay);
        imgtime = (TextView)itemView.findViewById(R.id.timing);

    }
    /*public void bindView() {
        imgtime.setText((int) new Date().getTime());
    }*/
}
