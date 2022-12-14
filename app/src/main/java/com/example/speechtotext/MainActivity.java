package com.example.speechtotext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected  static  final  int RESULT_SPEECH =1;
    private ImageButton btnSpeak;
    private TextView tvText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText= findViewById(R.id.tvText);
        btnSpeak=findViewById(R.id.btnSpeak);
        btnSpeak.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent intent =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Сизди угуп жатам...");
                intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, "ko-KR");
                intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, "ru-RU");
                intent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, "en-US");
                intent.putExtra(
                        RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 9000);
                try {
                    startActivityForResult(intent,RESULT_SPEECH);

                }catch (ActivityNotFoundException e ){
                    Toast.makeText(getApplicationContext(), "Ваше устройство не поддерживает Speech to Text плагин",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_SPEECH:
                if (resultCode == RESULT_OK && data != null){
                    //! got speech result
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //! show speech result in UI
                    tvText.setText("Сиздин созуңуз: "+ text.get(0));
                }
                break;
        }
    }
}