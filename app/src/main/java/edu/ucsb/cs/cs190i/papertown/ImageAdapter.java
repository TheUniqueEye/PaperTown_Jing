/*
 *  Copyright (c) 2017 - present, Xuan Wang
 *  All rights reserved.
 *
 *  This source code is licensed under the BSD-style license found in the
 *  LICENSE file in the root directory of this source tree.
 *
 */

package edu.ucsb.cs.cs190i.papertown;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by EYE on 08/05/2017.
 */

public class ImageAdapter extends BaseAdapter {

    private Context context;
    //private ArrayList<Bitmap> bitmapList;
    private ArrayList<Uri> uriList;

    public ImageAdapter(Context context, ArrayList<Uri> uriList) {
        this.context = context;
        //this.bitmapList = bitmapList;
        this.uriList = uriList;
    }

    public int getCount() {
        //return this.bitmapList.size();
        return this.uriList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageBitmap(this.bitmapList.get(position));

        Picasso.with(imageView.getContext()).load(this.uriList.get(position)).into(imageView);

        return imageView;
    }

}
