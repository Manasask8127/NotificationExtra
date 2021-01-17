package com.example.notificationexample;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        textView = findViewById(R.id.text);
        //getting the notification message
        //String message = getIntent().getStringExtra("message");
        //textView.setText(message);
    }


}
