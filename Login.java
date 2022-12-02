 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText uniqueid = findViewById(R.id.User);
        final EditText password = findViewById(R.id.Password);
        final Button loginButton = findViewById(R.id.LoginBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Declare a buffered reader
                BufferedReader bufferedReader;
                // Reading/writing is exception prone and will need to be encapsulated in a try/catch block.
                try {
                    // Create a new instance of a BufferedReader and provide an InputStreamReader with the file to read (and optionally, the encoding).
                    bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("loginDetails.csv"), StandardCharsets.UTF_8));
                    // Read one line at a time.
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        // Break each line into tokens (note that in CSV our values are comma separated)
                        String[] tokens = line.split(",");
                    /*
                    TODO:
                    If necessary, check whether each line is correct.
                    Determine if the username and password provided match a user.
                    */
                        boolean username = false;
                        boolean pw = false;
                        username = tokens[1].equals(uniqueid.getText().toString());
                        pw = tokens[2].equals(password.getText().toString());
                        System.out.println("user" + tokens[1]);

                        System.out.println("pw" + tokens[2]);
                        if (username && pw) {
                            User u = new User(
                                    Integer.parseInt(tokens[0]),
                                    tokens[1],
                                    tokens[2]);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("USER", u);
                            Toast.makeText(getApplicationContext(), "Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            bufferedReader.close();
                            return;
                        }
                    }
                    bufferedReader.close(); // Close the reader to prevent problems.
                    Toast.makeText(getApplicationContext(), "Not Valid", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    // TODO: What would you like to happen if this exception is thrown?
                    e.printStackTrace();
                } /*finally {

                    // TODO What would you like to happen regardless of whether an exception is thrown?
                }*/
            }

        });
    }
}
