package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;

import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {


    private int seconds ;

    //EditText editText = (EditText)findViewById(R.id.editTextAmount);
   // seconds= Integer.parseInt(editText.getText().toString());
    TextView tv ;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        ActionBar actionBar = getActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if(savedInstanceState !=null){




            seconds = savedInstanceState.getInt("seconds");

            running = savedInstanceState.getBoolean("running");
        }
        runTimer();



    }


    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);



        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);
    }


    public void onClickStart(View view) {
       EditText editText = (EditText)findViewById(R.id.editTextAmount);
        seconds= Integer.parseInt(editText.getText().toString());
       running = true;
    }

    public void onClickStop(View view) {
        running = false;
    }

    public void onClickReset(View view) {
        seconds = 0;
        running = false;

    }
    private void runTimer(){
        final TextView txtView = (TextView) findViewById(R.id.txtView);



        //seconds= Integer.parseInt(editText.getText().toString());

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {


                int hours = seconds/3600;
                int minutes = seconds % 3600 /60;
                int snds = seconds % 60;
                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d", hours, minutes, snds);
                txtView.setText(time);
                if(running){
                    --seconds;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

}
