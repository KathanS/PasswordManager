package com.example.passwordmanager;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordList extends AppCompatActivity {

    private static final String TAG = "PasswordList";

    DatabaseHelper mDatabaseHelper;
    DatabaseHelper2 mDatabaseHelper2;
    private Button btnAdd, btnViewData;
    private EditText editText,editText2;
    private String newEntry2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_list );
        editText =  findViewById(R.id.editText);
        btnAdd =  findViewById(R.id.btnAdd);
        btnViewData =  findViewById(R.id.btnView);
        editText2 = findViewById(R.id.editText2);

        mDatabaseHelper = new DatabaseHelper(this);
        mDatabaseHelper2 = new DatabaseHelper2(this);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
                newEntry2 = editText2.getText().toString();
                if (editText2.length() != 0) {
                    AddData2(newEntry2);
                    editText2.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editScreenIntent = new Intent(PasswordList.this, ListDataActivity.class);
                editScreenIntent.putExtra("code",newEntry2);
                startActivity(editScreenIntent);
            }
        });

    }

    public void AddData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }
    public void AddData2(String newEntry2) {
        boolean insertData = mDatabaseHelper2.addData(newEntry2);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("Something went wrong");
        }
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}