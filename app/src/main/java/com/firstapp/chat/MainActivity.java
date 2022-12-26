package com.firstapp.chat;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(e -> handleText());

        // initialkizing
        textView = findViewById(R.id.textView);
        inputText = findViewById(R.id.inputText);
    }

    public void handleText() {
        textView.setText(inputText.getText());
        System.out.println("Button Clicked");


    }
}