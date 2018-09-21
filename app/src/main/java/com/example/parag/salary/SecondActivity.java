package com.example.parag.salary;


import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends Activity {

    EditText uid,nm,exp;
    RadioGroup sc,rank;
    RadioButton rb1,rb2,sls,qlt,imp,clk,spv,mng;
    CheckBox ovrt,wknd,fstv,tour;
    Button add,del,disp,updt,sal,clr,det;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        uid=(EditText)findViewById(R.id.aadhar_no);
        nm=(EditText)findViewById(R.id.name);
        exp=(EditText)findViewById(R.id.experience);
        sc=(RadioGroup)findViewById(R.id.section);
        rank=(RadioGroup)findViewById(R.id.rank);
        sc.clearCheck();
        rank.clearCheck();
        ovrt=(CheckBox)findViewById(R.id.overtime);
        wknd=(CheckBox)findViewById(R.id.weekend);
        fstv=(CheckBox)findViewById(R.id.festival);
        tour=(CheckBox)findViewById(R.id.tour);
        add=(Button)findViewById(R.id.add);
        del=(Button)findViewById(R.id.remove);
        disp=(Button)findViewById(R.id.details);
        updt=(Button)findViewById(R.id.update);
        det=(Button)findViewById(R.id.alldetails);
        sal=(Button)findViewById(R.id.salary);
        clr=(Button)findViewById(R.id.clear);
        int a = sc.getCheckedRadioButtonId();
        rb1= (RadioButton)findViewById(a);
        int b = rank.getCheckedRadioButtonId();
        rb2= (RadioButton)findViewById(b);
        sls= (RadioButton)findViewById(R.id.sales);
        qlt= (RadioButton)findViewById(R.id.quality);
        imp= (RadioButton)findViewById(R.id.Import);
        clk= (RadioButton)findViewById(R.id.clerk);
        spv= (RadioButton)findViewById(R.id.supervisor);
        mng= (RadioButton)findViewById(R.id.manager);
        sc.clearCheck();
        rank.clearCheck();
        uid.setText("");
        nm.setText("");
        exp.setText("");

        del.setEnabled(false);
        disp.setEnabled(false);
        updt.setEnabled(false);
        sal.setEnabled(false);
        uid.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO Auto-generated method stub
                if(uid.getText().toString().isEmpty())
                    disp.setEnabled(true);
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
        try
        {
            db=openOrCreateDatabase("employee",SQLiteDatabase.CREATE_IF_NECESSARY, null);
            db.execSQL("create table if not exists salary(aadhar int primary key,name varchar(35),experience int ,section char(10),rank char(10),bonus1 char(20),bonus2 char(20),bonus3 char(20),bonus4 char(20),empsalary double)");
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"error :"+e.getMessage(),Toast.LENGTH_SHORT).show();


        }
        add.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                ContentValues cv= new ContentValues();
                cv.put("aadhar",uid.getText().toString());
                cv.put("name", nm.getText().toString());
                cv.put("experience", exp.getText().toString());
                int a = sc.getCheckedRadioButtonId();
                rb1= (RadioButton)findViewById(a);
                int b = rank.getCheckedRadioButtonId();
                rb2= (RadioButton)findViewById(b);
                cv.put("section", rb1.getText().toString());
                cv.put("rank", rb2.getText().toString());
                if(ovrt.isChecked())
                    cv.put("bonus1", ovrt.getText().toString());
                else
                    cv.put("bonus1","n/a");
                if(wknd.isChecked())
                    cv.put("bonus2", wknd.getText().toString());
                else
                    cv.put("bonus2","n/a");
                if(fstv.isChecked())
                    cv.put("bonus3", fstv.getText().toString());
                else
                    cv.put("bonus3","n/a");
                if(tour.isChecked())
                    cv.put("bonus4", tour.getText().toString());
                else
                    cv.put("bonus4","n/a");

                if(db.insert("salary",null,cv)!=-1)
                {
                    Toast.makeText(getApplicationContext(), "Inserted successfully", Toast.LENGTH_SHORT).show();
                    uid.setText("");
                    nm.setText("");
                    exp.setText("");
                    ovrt.setChecked(false);
                    wknd.setChecked(false);
                    fstv.setChecked(false);
                    tour.setChecked(false);
                    sc.clearCheck();
                    rank.clearCheck();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Insertion unsuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });

        disp.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                del.setEnabled(true);
                updt.setEnabled(true);
                sal.setEnabled(true);
                String s1,s2,s3,s4,s5,s6,s7,s8,s9;
                try
                {
                    String id= uid.getText().toString();
                    Cursor c= db.rawQuery("select * from salary where aadhar="+id,null);
                    if(c.moveToFirst())
                    {
                        s1=c.getString(0);
                        s2=c.getString(1);
                        s3=c.getString(2);
                        s4=c.getString(3);
                        s5=c.getString(4);
                        s6=c.getString(5);
                        s7=c.getString(6);
                        s8=c.getString(7);
                        s9=c.getString(8);
                        nm.setText(s2);
                        exp.setText(s3);
                        if(s4.equalsIgnoreCase("sales"))
                            sls.setChecked(true);
                        else if(s4.equalsIgnoreCase("quality"))
                            qlt.setChecked(true);
                        else
                            imp.setChecked(true);
                        if(s5.equalsIgnoreCase("clerk"))
                            clk.setChecked(true);
                        else if(s5.equalsIgnoreCase("supervisor"))
                            spv.setChecked(true);
                        else
                            mng.setChecked(true);
                        if(s6.equalsIgnoreCase("overtime"))
                            ovrt.setChecked(true);
                        if(s7.equalsIgnoreCase("weekend"))
                            wknd.setChecked(true);
                        if(s8.equalsIgnoreCase("festival"))
                            fstv.setChecked(true);
                        if(s9.equalsIgnoreCase("tour"))
                            tour.setChecked(true);

                    }

                    else
                        Toast.makeText(getApplicationContext(), "invalid id", Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "exception "+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
        clr.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                del.setEnabled(false);
                disp.setEnabled(false);
                updt.setEnabled(false);
                sal.setEnabled(false);
                uid.setText("");
                nm.setText("");
                exp.setText("");
                ovrt.setChecked(false);
                wknd.setChecked(false);
                fstv.setChecked(false);
                tour.setChecked(false);
                sc.clearCheck();
                rank.clearCheck();

            }
        });

        del.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String id= uid.getText().toString();
                int res= db.delete("salary","aadhar="+id, null);
                if(id.isEmpty())
                    Toast.makeText(getApplicationContext(), "please enter a valid aadhar no, then click on search button, henceforth go for deletion",Toast.LENGTH_SHORT).show();
                else if(res>0)
                {
                    Toast.makeText(getApplicationContext(), "deleted Successfully",Toast.LENGTH_SHORT).show();
                    uid.setText("");
                    nm.setText("");
                    exp.setText("");
                    ovrt.setChecked(false);
                    wknd.setChecked(false);
                    fstv.setChecked(false);
                    tour.setChecked(false);
                    sls.setChecked(false);
                    qlt.setChecked(false);
                    imp.setChecked(false);
                    clk.setChecked(false);
                    spv.setChecked(false);
                    mng.setChecked(false);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "deletion failed, invalid ID",Toast.LENGTH_SHORT).show();
                }
            }
        });
        updt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String id= uid.getText().toString();
                ContentValues cv= new ContentValues();
                int a = sc.getCheckedRadioButtonId();
                rb1= (RadioButton)findViewById(a);
                int b = rank.getCheckedRadioButtonId();
                rb2= (RadioButton)findViewById(b);
                cv.put("name",nm.getText().toString());
                cv.put("experience",exp.getText().toString());
                cv.put("section", rb1.getText().toString());
                cv.put("rank", rb2.getText().toString());
                if(ovrt.isChecked())
                    cv.put("bonus1", ovrt.getText().toString());
                else
                    cv.put("bonus1","n/a");
                if(wknd.isChecked())
                    cv.put("bonus2", wknd.getText().toString());
                else
                    cv.put("bonus2","n/a");
                if(fstv.isChecked())
                    cv.put("bonus3", fstv.getText().toString());
                else
                    cv.put("bonus3","n/a");
                if(tour.isChecked())
                    cv.put("bonus4", tour.getText().toString());
                else
                    cv.put("bonus4","n/a");


                int res= db.update("salary",cv,"aadhar="+id, null);
                if(res>0)
                {
                    Toast.makeText(getApplicationContext(), "Updated Successfully",Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(getApplicationContext(), "Updation failed",Toast.LENGTH_SHORT).show();
            }
        }) ;

        sal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                double bsal=0.0,ovt=0.0,wkd=0.0,fsv=0.0,tr=0.0,tsal=0.0;
                String empname = nm.getText().toString();
                String id= uid.getText().toString();
                int expr = Integer.parseInt(exp.getText().toString());
                bsal+=expr*2000.00;
                if(sls.isChecked())
                {
                    if(clk.isChecked())
                        bsal +=20000.00;
                    else if(spv.isChecked())
                        bsal +=50000.00;
                    else
                        bsal +=100000.00;
                }

                else if(qlt.isChecked())
                {
                    if(clk.isChecked())
                        bsal +=30000.00;
                    else if(spv.isChecked())
                        bsal +=65000.00;
                    else
                        bsal +=120000.00;
                }
                else
                {
                    if(clk.isChecked())
                        bsal +=15000.00;
                    else if(spv.isChecked())
                        bsal +=40000.00;
                    else
                        bsal +=850000.00;
                }
                if(ovrt.isChecked())
                {
                    ovt=8000.00;
                    tsal=bsal+ovt+wkd+fsv+tr;
                }
                if(wknd.isChecked())
                {
                    wkd=8000.00;
                    tsal=bsal+ovt+wkd+fsv+tr;
                }
                if(fstv.isChecked())
                {
                    fsv=8000.00;
                    tsal=bsal+ovt+wkd+fsv+tr;
                }
                if(tour.isChecked())
                {
                    tr=8000.00;
                    tsal=bsal+ovt+wkd+fsv+tr;
                }
                ContentValues cv= new ContentValues();
                cv.put("empsalary",tsal);
                db.insert("salary",null,cv);
                Intent i2= new Intent(SecondActivity.this,ThirdActivity.class);
                Bundle b1= new Bundle();
                b1.putString("name", empname);
                b1.putString("id",id);
                b1.putDouble("bsalary", bsal);
                b1.putDouble("overtime", ovt);
                b1.putDouble("weekend", wkd);
                b1.putDouble("festival", fsv);
                b1.putDouble("tour", tr);
                b1.putDouble("tsalary", tsal);
                i2.putExtras(b1);
                startActivity(i2);


            }
        });
        det.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                Intent i11= new Intent(SecondActivity.this,FourthActivity.class);
                startActivity(i11);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.second, menu);//
        return true;
    }

}
