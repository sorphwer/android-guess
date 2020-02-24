package com.example.android_guess;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.Random;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button checkButton;
    private Button backButton;
    private Button upButton;
    private Button downButton;
    //private Button backButtonLower;
    //private Button backButtonHigher;
    private EditText inputText;
    //private TextView resultLower;
    //private TextView resultHigher;
    private TextView hint;
    private TextView resultText;
    private static int length=4;
    private int temp;
    private int test=1234;
    private Random r;
    private Typeface consola;


    private String guessText;//save the value from main layout.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //remove ActionBar
        getSupportActionBar().hide();

        //init random number
        r = new Random();
        test=r.nextInt(9999)+1;
        Toast.makeText(MainActivity.this, Integer.toString(test), Toast.LENGTH_SHORT).show();

        //bind Component
        checkButton=findViewById(R.id.guess_button);
        inputText=findViewById(R.id.input_EditText);
        hint=findViewById(R.id.hint);
        hint.setTextColor(getResources().getColor(R.color.gray));
        resultText=findViewById(R.id.result_TextView);
        backButton=findViewById(R.id.back_button);
        upButton=findViewById(R.id.upButton);
        downButton=findViewById(R.id.downButton);



        //set fonts
        consola=Typeface.createFromAsset(getAssets(),"fonts/consola.ttf");
        //hint.setTypeface(consola);
        inputText.setTypeface(consola);
        resultText.setTypeface(consola);

        //Set listener
        checkButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        inputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(inputText.getText().toString().length()<=length && inputText.getText().toString().length()>0 ){
                    checkButton.setEnabled(true);
                }
                else {
                    checkButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //init button state
        checkButton.setEnabled(false);
        backButton.setEnabled(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.guess_button:
                //run a getText, compared with random number , and nav.
                guessText=inputText.getText().toString();
                temp=Integer.parseInt(guessText);
                if(temp>test){
                    //if higher:
                    resultText.setText(guessText);
                    inputText.setVisibility(View.INVISIBLE);
                    hint.setTextColor(getResources().getColor(R.color.gray));
                    resultText.setVisibility(View.VISIBLE);
                    hint.setText(R.string.higher);
                    checkButton.setBackgroundResource(R.drawable.bg_fail);
                    checkButton.setEnabled(false);
                    backButton.setVisibility(View.VISIBLE);
                    backButton.setEnabled(true);

                    upButton.setBackgroundResource(R.drawable.arrow_up_gray);
                    downButton.setBackgroundResource(R.drawable.arrow_down_red);
                    upButton.setVisibility(View.VISIBLE);
                    downButton.setVisibility(View.VISIBLE);
                }
                else if(temp<test){
                    //if lower:
                    resultText.setText(guessText);
                    inputText.setVisibility(View.INVISIBLE);
                    hint.setTextColor(getResources().getColor(R.color.gray));
                    resultText.setVisibility(View.VISIBLE);
                    hint.setText(R.string.lower);
                    checkButton.setBackgroundResource(R.drawable.bg_fail);
                    checkButton.setEnabled(false);
                    backButton.setVisibility(View.VISIBLE);
                    backButton.setEnabled(true);
                    upButton.setBackgroundResource(R.drawable.arrow_up_red);
                    downButton.setBackgroundResource(R.drawable.arrow_down_gray);
                    upButton.setVisibility(View.VISIBLE);
                    downButton.setVisibility(View.VISIBLE);
                }

                else{
                    //if success:
                    //inputText.setEnabled(false);
                    inputText.setVisibility(View.INVISIBLE);
                    checkButton.setBackgroundResource(R.drawable.bg_success);
                    checkButton.setEnabled(false);
                    hint.setText(R.string.success);
                    hint.setTextColor(getResources().getColor(R.color.colorAccent));
                    resultText.setText(guessText);
                    resultText.setVisibility(View.VISIBLE);

                    backButton.setVisibility(View.VISIBLE);
                    backButton.setEnabled(true);
                    test=r.nextInt(9999)+1;
                    Toast.makeText(MainActivity.this, Integer.toString(test), Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.back_button:
                checkButton.setBackgroundResource(R.drawable.button_guess);
                checkButton.setEnabled(false);
                inputText.setVisibility(View.VISIBLE);
                hint.setText(R.string.title);
                hint.setTextColor(getResources().getColor(R.color.gray));
                resultText.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.INVISIBLE);
                backButton.setEnabled(false);
                inputText.setText("");
                upButton.setVisibility(View.INVISIBLE);
                downButton.setVisibility(View.INVISIBLE);

                break;

        }


    }
}
