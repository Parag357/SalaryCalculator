package com.example.parag.salary;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parag.salary.R;

public class MainActivity extends Activity {

    TextView t1;
    EditText e1,e2;
    Button b1;
    View v1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1=(TextView)findViewById(R.id.textView1);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.validate);
      //  v1=findViewById(R.id.view1);
        e2.setEnabled(false);
        b1.setEnabled(false);
        e1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                if (e1.getText().toString().isEmpty())
                    e2.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });
        e2.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub

                if (e2.getText().toString().isEmpty())
                    b1.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }
        });
        b1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String nm=e1.getText().toString();
                String pwd=e2.getText().toString();
                if(nm.isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter your name", Toast.LENGTH_SHORT).show();
                else if(pwd.isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter your password", Toast.LENGTH_SHORT).show();
                else
                {
                    if(!(nm.equals("Parag")))
                    {
                        Toast.makeText(getApplicationContext(), "unauthorised name !!!!!!", Toast.LENGTH_SHORT).show();
                        e1.setText("");
                    }
                    else if(!(pwd.equals("Finoshia")))
                    {
                        Toast.makeText(getApplicationContext(), "wrong password !!!!!!", Toast.LENGTH_SHORT).show();
                        e2.setText("");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Validation Successfull", Toast.LENGTH_SHORT).show();
                        Intent i1= new Intent(MainActivity.this,SecondActivity.class);

                        startActivity(i1);
                    }
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.main, menu);//
        return true;
    }

}
