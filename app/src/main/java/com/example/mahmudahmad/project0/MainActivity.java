package com.example.mahmudahmad.project0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.*;


public class MainActivity extends AppCompatActivity {

    int attempts;

    EditText userName;
    EditText password;
    Button cancel;
    Button login;
    Toast errorMessage;

    public void onButtonTap(View v) {
        if(userName.getText().toString().equals("tt") && password.getText().toString().equals("tt")) {
            attempts = 5;
        } else {
            StringBuilder errorString = new StringBuilder("Number of attempts remaining: ");
            errorString.append(Integer.toString(attempts));
            errorMessage = Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_LONG);
            errorMessage.show();
            attempts--;
        }
    }

    public void onCancel(View v) {
        userName.setText("");
        password.setText("");
    }

    public void linkComponents() {
        userName = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        cancel = (Button) findViewById(R.id.button);
        login = (Button) findViewById(R.id.button2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linkComponents();
        attempts = 5;

    }
}