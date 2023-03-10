package com.example.foreground;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button btnStartService;
    private Button btnStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_data_intent);
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopService = findViewById(R.id.btn_stop_service);

        btnStartService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clickStartService();
            }
        });

        btnStopService.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clickStopService();
            }
        });
    }

    private void clickStartService(){
        Intent intent = new Intent(this,MyService.class);
        //----Project 1
       // intent.putExtra("key_data",editText.getText().toString().trim());

        /////---------Project 2
        Song song = new Song("Music","Thang",R.drawable.img_music,R.raw.music);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song",song);
        intent.putExtras(bundle);
        startService(intent);
    }

    private void clickStopService(){
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
    }
}