package com.example.passwordmanager;


 import android.content.Intent;
 import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

    public class MainActivity extends AppCompatActivity {


        private SharedPreferences mPreferences;
        private SharedPreferences.Editor mEditor;

        private EditText name,password;
        private Button btn,btnAc;
        private CheckBox box;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            name = findViewById(R.id.name);
            password = findViewById(R.id.password);

            btn = findViewById(R.id.btn);
            btnAc = findViewById(R.id.btnAc);

            box = findViewById(R.id.box);

            mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            mEditor = mPreferences.edit();

            String tookN = mPreferences.getString("loginName","");
            String tookP = mPreferences.getString("loginPassword","");

            if(!tookN.equals("") && !tookP.equals(""))
                btnAc.setEnabled(false);



            check();

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(box.isChecked()){
                        mEditor.putString("box", "True");
                        mEditor.apply();

                        String takeName = name.getText().toString();
                        mEditor.putString("name",takeName);
                        mEditor.commit();

                        String takePassword = password.getText().toString();
                        mEditor.putString("password",takePassword);
                        mEditor.commit();

                    }else{
                        mEditor.putString("box", "False");
                        mEditor.commit();


                        mEditor.putString("name","");
                        mEditor.commit();

                        mEditor.putString("password","");
                        mEditor.commit();


                    }

                    //verification

                    int flag=0;

                    if((name.getText().toString().equals(mPreferences.getString("loginName","kathan"))) && (password.getText().toString().equals(mPreferences.getString("loginPassword","kathan"))) )
                        flag=1;




                    if(flag==1)
                    {
                        Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
                        startActivity(intent);
                    }


                }
            });




            btnAc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,set_password.class);
                    startActivity(intent);
                }
            });


        }



        private void check(){
            String box2=mPreferences.getString("box","False");
            String name2=mPreferences.getString("name","");
            String password2=mPreferences.getString("password","");

            name.setText(name2);
            password.setText(password2);


            if(box2.equals("True")){
                box.setChecked(true);
            }else{
                box.setChecked(false);
            }
        }




    }
