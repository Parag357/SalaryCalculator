package com.example.parag.salary;
import android.R.id;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parag.salary.SecondActivity;

public class FourthActivity extends Activity {

    ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        lv1=(ListView)findViewById(R.id.listView1);
        String[] sb= new String[100];
        try
        {
            Cursor c1= SecondActivity.db.rawQuery("select * from salary",null);
            int i=0;
            while(c1.moveToNext())
            {
                sb[i]="ID:"+c1.getString(0)+" \n Name:"+c1.getString(1)+" \n Experience:"+c1.getString(2)+"years \n Department:"+c1.getString(3)+" \n rank:"+c1.getString(4)+" \n Bonus 1:"+c1.getString(5)+" \n Bonus 2:"+c1.getString(6)+" \n Bonus 3:"+c1.getString(7)+" \n Bonus 4:"+c1.getString(8)+" \n";
                i++;
            }
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.customlist,sb);
            lv1.setAdapter(adapter);
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Exception : "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.fourth, menu);//
        return true;
    }

}
