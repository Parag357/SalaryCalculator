package com.example.parag.salary;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
public class ThirdActivity extends Activity {

    TextView t1;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        t1=(TextView)findViewById(R.id.textView1);
        b=(Button)findViewById(R.id.validate);
        Intent i3= getIntent();
        Bundle b2= i3.getExtras();
        String name= b2.getString("name");
        String id1=b2.getString("id");
        double bsal=b2.getDouble("bsalary");
        double ovrt=b2.getDouble("overtime");
        double wknd=b2.getDouble("weekend");
        double fstv=b2.getDouble("festival");
        double tour=b2.getDouble("tour");
        double tsal=b2.getDouble("tsalary");
        t1.setText(" ID is : "+id1+" \n \n name is : "+name+" \n \n Base Salary is : "+bsal+" \n \n Overtime Bonus is : "+ovrt+" \n \n Weekend Bonus is : "+wknd+" \n \n Festival Bonus is : "+fstv+" \n \n Tour Bonus is : "+tour+" \n \n Total Salary is : "+tsal);

        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                System.exit(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.third, menu);//
        return true;
    }

}
