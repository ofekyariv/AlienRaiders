package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

public class Aliens extends Objects {
    public float dx;
    public  float dy;

    public Aliens(Context context, float x, float y, Bitmap bitmap) {
        super(context, x, y, bitmap);

        this.bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.aliens);
        this.dx= (float) 0.5;
        this.dy= 50;

    }


    public float getDx() {

        return dx;
    }

    public void setDx(float dx) {

        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }
}
