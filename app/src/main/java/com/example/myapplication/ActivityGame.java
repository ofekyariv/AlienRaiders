package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityGame extends AppCompatActivity implements View.OnClickListener {

    BoardGame boardGame;
    Dialog dialog;
    Button btnGameOver;
    Button again;
    TextView score;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game);

        dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog);

        boardGame= new BoardGame(this,dialog);

        LinearLayout linearLayout =findViewById(R.id.game);
        linearLayout.addView(boardGame);

        getSupportActionBar().hide();//removing the ActionBar


        btnGameOver = dialog.findViewById(R.id.btnGameOver);
        btnGameOver.setOnClickListener(this);

        again = dialog.findViewById(R.id.btnAgain);
        again.setOnClickListener(this);

    }

    public void gameOver ()
    {
        score=dialog.findViewById(R.id.tvScore);
        score.setText("your score:"+boardGame.score);
    }

    @Override
    public void onClick(View view) {
        if (view == btnGameOver)
        {
           // dialog.dismiss();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);

           // Toast.makeText(this,"bhg",Toast.LENGTH_SHORT).show();
        }
        if (view==again)
        {
            Intent intent = new Intent(this,ActivityGame.class);
            startActivity(intent);
        }


    }

}