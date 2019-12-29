package com.example.passwordmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class set_password extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor edit;
    EditText name,password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_password);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        Button done = findViewById(R.id.done);

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        edit = pref.edit();





        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getName = name.getText().toString();
                String getPassword = password.getText().toString();

                edit.putString("loginName",getName);
                edit.apply();
                edit.putString("loginPassword",getPassword);
                edit.apply();

                Intent intent = new Intent(set_password.this,MainActivity.class);
                startActivity(intent);
            }
        });






    }
}
