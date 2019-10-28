package com.example.caspe.sozluk;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private Button openMic;
    private EditText showVoiceText;
    private TextView anlam;
    private Button btnara;
    private final int REO_CODE_SPEECH_OUTPUT=143;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnara=findViewById(R.id.button2);
        anlam=findViewById(R.id.textView2);

        btnara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
                databaseAccess.open();
                String n=showVoiceText.getText().toString();
                String anlami =databaseAccess.getAnlami(n);

                anlam.setText(anlami);

                databaseAccess.close();


            }
        });

        openMic = (Button)findViewById(R.id.button);
        showVoiceText=(EditText)findViewById(R.id.showVoiceOutput);

        openMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnToOpenMic();
            }
        });


    }
    private void  btnToOpenMic(){
        Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Lütfen kelimenizi söyleyin");
        try {
            startActivityForResult(intent,REO_CODE_SPEECH_OUTPUT);

        }
        catch (ActivityNotFoundException tim){

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REO_CODE_SPEECH_OUTPUT: {
                if(resultCode ==RESULT_OK && null!=data) {
                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    showVoiceText.setText(voiceInText.get(0));

                }
                break;
                }
            }
        }
    }

