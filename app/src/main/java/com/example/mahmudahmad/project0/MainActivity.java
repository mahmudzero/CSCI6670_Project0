package com.example.mahmudahmad.project0;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.inputmethod.InputMethodManager;
import com.google.gson.*;
import java.io.*;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    int attempts;
    boolean allowAttempts;

    EditText userName;
    EditText password;
    Button cancel;
    Button loginButton;
    Toast errorMessage;
    String butterfly;
    String worm;

    public void test() {
        JsonObject jsonObject = new JsonObject();
        JsonArray data;
        InputStream is = null;
        is = getResources().openRawResource(R.raw.dependencies);
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new BufferedReader(new InputStreamReader(is)));
        jsonObject = jsonElement.getAsJsonObject();
        data = jsonObject.get("data").getAsJsonArray();
        butterfly = data.get(0).getAsJsonObject().get("butterfly").getAsString();
        worm = data.get(0).getAsJsonObject().get("worm").getAsString();
    }

    public void login() {
        if(userName.getText().toString().equals(butterfly) && password.getText().toString().equals(worm) && allowAttempts) {
            errorMessage = Toast.makeText(getApplicationContext(), "You cracked the code!", Toast.LENGTH_SHORT);
            errorMessage.show();
            attempts = 5;
            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(intent);
        } else if(attempts > 0) {
            StringBuilder errorString = new StringBuilder("Number of attempts remaining: ");
            errorString.append(Integer.toString(attempts));
            errorMessage = Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT);
            errorMessage.show();
            attempts--;
        } else {
            errorMessage = Toast.makeText(getApplicationContext(), "No more attempts! You have failed.", Toast.LENGTH_SHORT);
            errorMessage.show();
            allowAttempts = false;
            Intent intent = new Intent(MainActivity.this, Main3Activity.class);
            startActivity(intent);
        }
    }

    public void onLogin(View v) {
        login();
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    public void onCancel(View v) {
        userName.setText("");
        password.setText("");
        errorMessage = Toast.makeText(getApplicationContext(), "Fields Cleared", Toast.LENGTH_SHORT);
        errorMessage.show();
        InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
    }

    public void linkComponents() {
        userName = (EditText) findViewById(R.id.editText);
        password = (EditText) findViewById(R.id.editText2);
        cancel = (Button) findViewById(R.id.button);
        loginButton = (Button) findViewById(R.id.button2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
        linkComponents();
        attempts = 5;
        allowAttempts = true;
        TextView.OnEditorActionListener returnListener = new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView password, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_NULL && event.getAction() == KeyEvent.ACTION_DOWN);
                login();
                return false;
            }
        };
        password.setOnEditorActionListener(returnListener);
    }
}