package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityWelcome extends AppCompatActivity implements View.OnClickListener {
TextView tv;
Button btnInstructions,btnGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tv=findViewById(R.id.tv);
        Intent intent=getIntent();
        String user=intent.getExtras().getString("user");
        tv.setText("welcome\n"+user);

        btnInstructions=findViewById(R.id.btnInstructions);
        btnInstructions.setOnClickListener(this);

        btnGame=findViewById(R.id.btnGame);
        btnGame.setOnClickListener(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,Login.class);
            startActivity(intent);

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v==btnInstructions)
        {
            Intent intent = new Intent(this,Instructions.class);
            startActivity(intent);
        }
        if (v==btnGame)
        {
            Intent intent = new Intent(this,ActivityGame.class);
            startActivity(intent);
        }
    }
}
