package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpaceShip extends Objects {
    GunShot g;
    public SpaceShip(Context context, float x, float y, Bitmap bitmap) {
        super(context, x, y, bitmap);
        this.bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.spaceship);

    }

    public GunShot getG() {
        return g;
    }

    public void setG(GunShot g) {
        this.g = g;
    }

    public boolean didUserTouchMe(float xt, float yt)
    {
       if (xt>=this.getX() && xt<=this.getX()+bitmap.getWidth()
       && yt >= getY() && yt<=this.getY()+bitmap.getHeight())
       {
           return true;
       }

       return false;
    }

}
