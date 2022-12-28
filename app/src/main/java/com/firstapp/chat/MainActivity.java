package com.firstapp.chat;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing
        textView = findViewById(R.id.textView);
        inputText = findViewById(R.id.inputText);

        // python initialization
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        // create python instance
        Python py = Python.getInstance();

        //now create python object
        PyObject pyobj = py.getModule("python_script");


        // Button
        inputText.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    handleText(inputText.getText().toString(), pyobj);
                }
                return false;
            }
        });

        // Making the text scrollable
        textView.setMovementMethod(new ScrollingMovementMethod());

    }


    public void handleText(String input_text, PyObject pyobj) {
        System.out.println(input_text);
        PyObject result = pyobj.callAttr("main", input_text);
        System.out.println("Got result");
        textView.setText(result.toString());
        System.out.println("Button Clicked");

    }
}