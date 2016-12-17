package org.androidtown.lbs.map;

import android.net.Uri;

/**
 * Created by songmho on 2014-08-03.
 */
public class Listviewitem {
    private Uri image_url;
    private String title;

    public Uri getURL(){return image_url;}
    public String getTitle(){return title;}

    public Listviewitem(Uri url, String title){
        this.image_url=url;
        this.title=title;
    }
}
