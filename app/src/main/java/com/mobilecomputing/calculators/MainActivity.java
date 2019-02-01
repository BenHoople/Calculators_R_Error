package com.mobilecomputing.calculators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double billAmount = 0.00;
    private double tipPercent = 15;

    private EditText billTotal; //how much was charged

    private TextView percentView; //how much the percentage is on the slider
    private SeekBar percentSeekBar;//the slider itself

    private TextView tipTotal; //how much the tip is

    private TextView grandTotal;//how much to bill is with the tip

    //image
    private ImageView frontRight;

    //text-watcher to respond to the slider
    TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            //get currently displayed bill amount
            if(s.length()>0) {
                billAmount = Double.parseDouble(s.toString());
            }else{
                billAmount = 0.00;
            }
            //calc and display the tip and total
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void calculate() {
        //set%


        percentView.setText(String.valueOf(tipPercent));
        //double tipAmount = 0;
        double tipAmount= (billAmount*tipPercent)/ 100;
        tipTotal.setText(String.valueOf(tipAmount));

        //double total= 0;
        double total = tipAmount + billAmount;

        grandTotal.setText(String.valueOf(total));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //image
        frontRight = findViewById(R.id.frontRight);
        //associations
        billTotal = findViewById(R.id.billTotal);
        percentView = findViewById(R.id.percent);
        percentSeekBar = findViewById(R.id.seekBar);
        tipTotal = findViewById(R.id.TipDollarAmount);
        grandTotal = findViewById(R.id.totalDollarAmount);

        //bill total calculations
        billTotal.addTextChangedListener(amountEditTextWatcher);

        //Seekbar Change listener
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercent = progress;
            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            public void onStopTrackingTouch(SeekBar seekBar) {
                percentView.setText(String.valueOf(tipPercent));
                //percent change listener
                calculate();
                tipValidator(tipPercent);
            }
        });

//        frontRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(MainActivity.this, calculator.class);
//                startActivity(intent);
//            }
//        });

    }
    public void tipValidator(double tipPercent){
        if(tipPercent==30) {
            Toast.makeText(MainActivity.this, "Damn, Service must have been great!",
                    Toast.LENGTH_SHORT).show();
        }
        if(tipPercent>=20&&tipPercent<30) {
            Toast.makeText(MainActivity.this, "Wow! You're feeling generous!",
                    Toast.LENGTH_SHORT).show();
        }
        if(tipPercent>=10&&tipPercent<20) {
            Toast.makeText(MainActivity.this, "Pretty standard service",
                    Toast.LENGTH_SHORT).show();
        }
        if(tipPercent<=10) {
            Toast.makeText(MainActivity.this, "Service must have been pretty bad eh?",
                    Toast.LENGTH_SHORT).show();
        }
        if(tipPercent==0) {
            Toast.makeText(MainActivity.this, "Shiiiiit, ask for a refund!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
