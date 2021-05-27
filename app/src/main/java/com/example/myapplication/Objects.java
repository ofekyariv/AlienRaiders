package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

public class Objects extends View {
    private float x;// the x location
    private float y;// the y location
    Bitmap bitmap;

    public Objects(Context context, float x, float y, Bitmap bitmap) {
        super(context);
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
    }

    @Override
    public float getX() {
        return x;
    }

    public void setX(float x) {

        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public Bitmap getBitmap() {

        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
