package com.firstapp.chat;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        inputText.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                // to clear focus on Done button
                inputText.clearFocus();

                // log
                Toast toast = Toast.makeText(MainActivity.this,"ChatGPT loading response...", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM| Gravity.CENTER_HORIZONTAL,0,0);
                toast.show();
                Log.d("tag", "ChatGPT loading response...");

                // response
                handleText(inputText.getText().toString(), pyobj);

            }
            return false;
        });

        // to select all on focus
        inputText.setSelectAllOnFocus(true);

        // Making the text scrollable
        textView.setMovementMethod(new ScrollingMovementMethod());

    }

    public void handleText(String input_text, PyObject pyobj) {
        Log.d("tag", input_text);
        PyObject result = pyobj.callAttr("main", input_text);
        Log.d("tag", "Got result");
        textView.setText(result.toString());
        Log.d("tag", "Printed result");

    }
}