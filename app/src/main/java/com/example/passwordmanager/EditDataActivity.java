package com.example.passwordmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class EditDataActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item,password;

    DatabaseHelper mDatabaseHelper;
    DatabaseHelper2 mDatabaseHelper2;

    private String selectedName;
    private String selectedName2;
    private int selectedID; //,selectedID2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave =  findViewById(R.id.btnSave);
        btnDelete =  findViewById(R.id.btnDelete);
        editable_item =  findViewById(R.id.editable_item);
        mDatabaseHelper = new DatabaseHelper(this);
        mDatabaseHelper2 = new DatabaseHelper2(this);
        password = findViewById(R.id.passwordApp);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();


        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value


        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");
        selectedName2 = receivedIntent.getStringExtra("code");

        //System.out.println(selectedName2);

        //set the text to show the current selected name
        editable_item.setText(selectedName);
        Cursor data= mDatabaseHelper2.getData();

        int n =selectedID-1;

        for(int j=0;j<selectedID;j++)
        data.moveToNext();

        //String id = data.getString(selectedID);

        final String k = data.getString(1);

        password.setText(k);

        //password.setText(id);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateName(item,selectedID,selectedName);
                }else{
                    toastMessage("You must enter a name");
                }
                String item2 = password.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper2.updateCode(item2,selectedID,k);
                }else{
                    toastMessage("You must enter a password");
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteName(selectedID,selectedName);
                editable_item.setText("");
                mDatabaseHelper2.deleteCode(selectedID,k);
                password.setText("");
                toastMessage("removed from database");
            }
        });

    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
