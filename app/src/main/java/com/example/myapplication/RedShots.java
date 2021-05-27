package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class RedShots extends Objects {
    public RedShots(Context context, float x, float y, Bitmap bitmap) {
        super(context, x, y, bitmap);

        this.bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.redshot);
    }
}
