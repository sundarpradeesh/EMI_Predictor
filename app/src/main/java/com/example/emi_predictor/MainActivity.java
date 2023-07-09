package com.example.emi_predictor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button submits;
    EditText principal;
    SeekBar year;
    Spinner interest;
    TextView result;
    int n;
    double p,r,amount=0;
    String[] rates={"5.0","5.5","6.0","6.5","7.2","8.0","10.0","12.0","14.0","15.0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        interest= (Spinner) findViewById(R.id.spinner);
        year= (SeekBar) findViewById(R.id.seekBar);
        principal=(EditText)findViewById(R.id.editTextNumber);

        result=(TextView)findViewById(R.id.textView6);
        submits=(Button) findViewById(R.id.button);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, rates);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interest.setAdapter(adapter);
        year.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                n=progress;

                Toast.makeText(MainActivity.this, String.valueOf(n),
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        submits.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                try{
                    p=Double.parseDouble(principal.getText().toString());
                    r=Double.parseDouble(interest.getSelectedItem().toString());
                    r=r/12/100;
                    n=n*12;

                    amount =p*r* Math.pow((1+r),n )/(Math.pow((1+r),n)-1);
                    DecimalFormat decfor = new DecimalFormat("0.00");

                    result.setText("Amount to be paid\r\n"+String.valueOf(decfor.format(amount)));
                }
                catch(Exception e)
                {
                    result.setText("Incorrect Data!");
                }
            }
        });






    }

}