package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    public static final String HEIGHT = "HEIGHT";
    public static final String WEIGHT = "WEIGHT";
    public static final String GENDER="GENDER";

    public static final String FLAG = "FLAG";

    private EditText edtName;
    private EditText edtheight;
    private EditText edtweight;
    private Spinner s;
    private Button b;
    private Button bTime;
    private Button Calculate;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    TextView result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupSharedPrefs();
        checkPrefs();


        Calculate = (Button) findViewById(R.id.BMI);
        result = findViewById(R.id.bmiRes);
        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("BMI is"+ CalculateBMI());
            }
        });


        Spinner genderSpinner = (Spinner) findViewById(R.id.idgender);
        ArrayAdapter<String> myAdpt = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
        myAdpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(myAdpt);
       // genderSpinner.setOnItemSelectedListener(this);

        bTime = (Button) findViewById(R.id.timer);
        bTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(in);
            }
        });

    }

    private void setupSharedPrefs() {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();




    }

    private void setupViews() {

        edtName = findViewById(R.id.idname);
        edtheight = findViewById(R.id.idheight);
        edtweight = findViewById(R.id.idweight);
        b = findViewById(R.id.SAVE);

    }

    private void checkPrefs() {
        boolean flag = prefs.getBoolean(FLAG, false);
        if (flag) {
            String name = prefs.getString(NAME, "");
            String height = prefs.getString(HEIGHT, "");
            String weight = prefs.getString(WEIGHT, "");

            String gender=prefs.getString(GENDER,"");

            edtName.setText(name);
            edtheight.setText(height);
            edtweight.setText(weight);
            b.setClickable(true);
           //s.getItemAtPosition(gender.indexOf(GENDER));
        }
    }


    public void btnSaveOnClick(View view) {
        String name = edtName.getText().toString();
        String height = edtheight.getText().toString();
        String weight = edtweight.getText().toString();
        if (b.isClickable()) {
            editor.putString(NAME, name);
            editor.putString(HEIGHT, height);
            editor.putString(WEIGHT, weight);
            editor.putBoolean(FLAG,true);
            editor.commit();
        }
    }

    private float CalculateBMI() {
        String heightStr = edtheight.getText().toString();
        String weightStr = edtweight.getText().toString();
        float bmii=0;
        if (heightStr != null && !"".equals(heightStr) && weightStr != null && !"".equals(weightStr)) {
            float heightvalue = Float.parseFloat(heightStr);
            float weightvalue = Float.parseFloat(weightStr);
            bmii = weightvalue / (heightvalue * heightvalue);


        }
        return bmii;
    }

}