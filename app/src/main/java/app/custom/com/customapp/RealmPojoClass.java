package app.custom.com.customapp;

import java.util.ArrayList;

/**
 * Created by Ibrahim on 28-12-2016.
 */
public class RealmPojoClass extends io.realm.RealmObject {
  String imagePath;
    String time;
    String date;

    public RealmPojoClass() {
    }

    public RealmPojoClass(String imagepath, String time) {
        this.time=time;
        this.imagePath=imagepath;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}



