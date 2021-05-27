
package com.example.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

public class BoardGame extends View {
    public Context context;
    Handler handler;
    ThreadGame threadGame;
    SpaceShip s;
    ArrayList<Aliens> aliens;
    ArrayList <GunShot> shots;
    ArrayList <RedShots> redshots;
    Random rnd;
    GunShot g;
    RedShots t;
    Aliens alien;
    Bitmap s1 = null;
    int counter=0;
    boolean stopGame=false;
    Dialog dialog;
    int score;
    int life=0;

    public BoardGame(final Context context,Dialog dialog) {
        super(context);
        this.context = context;

        s = new SpaceShip(context, 100, 780, s1);

        g=new GunShot(context,s.getX(),s.getY(),s1);
        shots=new ArrayList<GunShot>();
        shots.add(g);


         redshots=new ArrayList<RedShots>();


        

        this.dialog = dialog;



        aliens=new ArrayList<Aliens>();
        int y = 100;
        for (int i = 0; i < 3; i++) {//3
            int x = 100;

            for (int j = 0; j < 5; j++) {//5
                alien=new Aliens(context,x,y,s1);
                aliens.add(alien);
                x = x + 150;
            }
            y = y + 150;

        }
        //Bitmap s2 = BitmapFactory.decodeResource(getResources(),R.drawable.alien);
        //  canvas.drawBitmap(s2,200,200,null);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                 if (!stopGame)
                 {
                     counter++;
                     if (counter==300)
                     {
                         counter=0;
                        int ilay = grill();
                         addNewShot(ilay);
                     }
                     progressShots();
                    int maximum= maxLocation();
                     changeAlien(maximum);
                     isCollision();
                     if( userLose() || life==3)
                         stopGame = true;
                     if(aliens.isEmpty())
                     {
                         int y = 100;
                         for (int i = 0; i < 3; i++) {
                             int x = 100;

                             for (int j = 0; j < 5; j++) {
                                 alien=new Aliens(context,x,y,s1);
                                 aliens.add(alien);
                                 x = x + 150;
                             }
                             y = y + 150;

                         }
                     }


                     invalidate(); // clear the canvas and calls to onDraw()
                 }
                 else
                 {
                  //   Toast.makeText(BoardGame.this.context, "Play again", Toast.LENGTH_SHORT).show();

                    // btnGameOver=findViewById(R.id.btnGameOver);
                    // btnGameOver.setOnClickListener((OnClickListener) context);
                   createNewDialog();

                 }

                return false;
            }
        });

        threadGame = new ThreadGame(handler);
        threadGame.start();

    }

    private int grill() {
         rnd=new Random();
        int alienshot=rnd.nextInt(aliens.size());
        String string = Integer.toString(alienshot);//Now it will return "10"

        //Toast.makeText(context, string , Toast.LENGTH_SHORT).show();
        return alienshot;

    }

    private int maxLocation() {
        int max=0;
        int index=0;
        for (int i =0; i < aliens.size(); i++ )
        {
          if (aliens.get(i).getX()>max)
          {
              max=(int) aliens.get(i).getX();
              index=i;
          }

        }
        return index;
    }

    private boolean userLose() {
        for (int i=0; i<aliens.size(); i++)
        if (aliens.get(i).getY()+aliens.get(i).getBitmap().getHeight()>=s.getY()+120 && s.getY()+s.getBitmap().getHeight()+120 >= aliens.get(i).getBitmap().getHeight())
            return  true;
        return false;
    }

    private void createNewDialog() {

        dialog.show();
       ((ActivityGame)(context)).gameOver();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draws the ship
        canvas.drawBitmap(s.getBitmap(), s.getX(), s.getY(), null);

//canvas.drawBitmap(t.getBitmap(),t.getX(), t.getY(), null);
         //draws the aliens

        for (int i=0; i<aliens.size(); i++)
        {
            canvas.drawBitmap(aliens.get(i).getBitmap(),aliens.get(i).getX(),aliens.get(i).getY(),null);
        }

        //draws the gunshot
        for (int i=0; i<shots.size(); i++)
        {
            canvas.drawBitmap(shots.get(i).getBitmap(),shots.get(i).getX(),shots.get(i).getY(),null);
        }


        for (int i=0; i<redshots.size(); i++)
        {
            canvas.drawBitmap(redshots.get(i).getBitmap(),redshots.get(i).getX(),redshots.get(i).getY(),null);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (s.didUserTouchMe(event.getX(), event.getY())) {
                s.setX(event.getX()-s.bitmap.getWidth()/2);

                   // Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show();
            }
        }
        if(event.getAction() == MotionEvent.ACTION_UP && s.didUserTouchMe(event.getX(), event.getY()))
        {
            s.setX(event.getX()-s.bitmap.getWidth()/2);

        }
        return true;
    }


    private void changeAlien(int index) {
        //if the aliens touch the border of the canvas
        if (aliens.get(0).getX() <= 0 || aliens.get(index).getX()>= this.getWidth() - 300)
        {

            for (int i = 0; i < aliens.size(); i++) {


                    aliens.get(i).setDx(-aliens.get(i).getDx());
                    aliens.get(i).setY(aliens.get(i).getY() + aliens.get(i).getDy());

            }
        }


        for (int i = 0; i < aliens.size(); i++) {


                aliens.get(i).setX(aliens.get(i).getX() + aliens.get(i).getDx());


        }

    }

    private void progressShots() {
        for (int i=0; i<shots.size(); i++)
        {
            shots.get(i).setY((float) (shots.get(i).getY()-2));
        }

        for (int i=0; i<redshots.size(); i++)
        {
            redshots.get(i).setY((float) (redshots.get(i).getY()+2));
        }
    }

    private void addNewShot( int index) {
        g=new GunShot(context,s.getX(),s.getY(),s1);
        shots.add(g);

        t=new RedShots(context,aliens.get(index).getX(),aliens.get(index).getY(),s1);
        redshots.add(t);


    }

    private void isCollision() {
        float yshot;
        float yalien;
        float alienHeight;
        float xshot;
        float xalien;
        float alienWidth;

        for (int i = 0; i < shots.size(); i++) {

                for (int j = 0; j < aliens.size(); j++)
                {
                 if (shots.get(i)!=null)
                 {
                     yshot = shots.get(i).getY();
                     yalien = aliens.get(j).getY();
                     alienHeight = aliens.get(j).getBitmap().getHeight();
                     xshot = shots.get(i).getX();
                     xalien = aliens.get(j).getX();
                     alienWidth = aliens.get(j).getBitmap().getWidth();
                     if (yshot+40 <= yalien + alienHeight && yshot+40 >= yalien
                     && xshot>=xalien && xshot<= xalien +alienWidth)
                     {

                         //Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show();
                         score++;
                         aliens.remove(j);
                         shots.remove(i);

                          return ;

                     }

                 }

                }
        }


        for (int i=0; i<redshots.size();i++)
        {
            if (redshots.get(i)!=null)
            {
            float xred=redshots.get(i).getX();
            float yred=redshots.get(i).getY();
            float ySpace=s.getY();
            float sHeight=s.getBitmap().getHeight();
            float xSpace=s.getX();
            float sWidth=s.getBitmap().getWidth();


              if (xred>=xSpace&& xred <=xSpace +sWidth && yred>=ySpace && yred<=ySpace +sHeight)
              {
                  //Toast.makeText(context, "onClick", Toast.LENGTH_SHORT).show();
                  redshots.remove(i);
                  life++;
                 // Toast.makeText(context, Integer.toString(life) , Toast.LENGTH_SHORT).show();
                  return;
              }
             // if (life==3)

          }
        }
    }

}

