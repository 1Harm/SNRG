package com.example.snrg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity   implements AdapterView.OnItemSelectedListener {
    String[] courses = { "Арендатор 1", "Арендатор 2",
            "Арендатор 3","Арендатор 4","Арендатор 5","Арендатор 6","Арендатор 7",
            "Арендатор 8","Арендатор 9"};
    String[] floor = { "-1","1","2"};
    String[] category = { "-1","1","2","3"};
    String[] zone = { "-1","Север 1","Север 2","Юг 1","Юг 2","Восток 1", "Восток 2", "Запад 1", "Запад 2"};
    String[] date={"March", "April","May","June","July","August","September","October","November"};
    TextView t1;
    String selectedItem1 = "";
    String selectedItem2 = "";
    String text = "";
    String number = "";
    String number2 = "";
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private EditText   mSellEditText, mCheckEditText, mEnterEditText;
    private Spinner mNameSpinner, mDateSpinner;
    private Button mAddButton, mViewButton;

    private SQLiteDatabase mDatabase;

    public EditText e1, e2,e;
    int num, num1, num2;
    public static final String EXTRA_TEXT = "com.example.snrg.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "com.example.snrg.EXTRA_NUMBER";
    public boolean getNumbers() {
        e=(EditText) findViewById(R.id.num);
        e1 = (EditText) findViewById(R.id.num1);

        e2 = (EditText) findViewById(R.id.num3);
        t1 = (TextView) findViewById(R.id.result);
        String s= e.getText().toString();
        String s1 = e1.getText().toString();
        String s2 = e2.getText().toString();


        if(s1.equals("Please enter value 2") && s2.equals(null))
        {
            String result = "Please enter value 3";
            e2.setText(result);
            return false;
        }
        if(s1.equals(null) && s2.equals("Please enter value 3"))
        {
            String result = "Please enter value 2";
            e1.setText(result);
            return false;
        }
        if(s.equals(null) && s1.equals("Please enter value 1"))
        {
            String result = "Please enter value 1";
            e1.setText(result);
            return false;
        }
        if(s1.equals("Please enter value 1") || s2.equals("Please enter value 2"))
        {
            return false;
        }

        if((!s1.equals(null) && s2.equals(null))|| (!s1.equals("") && s2.equals("")) ){

            String result = "Please enter value 2";

            e2.setText(result);
            return false;
        }
        if((s1.equals(null) && !s2.equals(null))|| (s1.equals("") && !s2.equals("")) ){
            String result = "Please enter value 1";
            e1.setText(result);
            return false;
        }
        if((s1.equals(null) && s2.equals(null))|| (s1.equals("") && s2.equals("")) ){
            String result1 = "Please enter value 1";
            e1.setText(result1);
            String result2 = "Please enter value 2";
            e2.setText(result2);
            return false;
        }

        else {
            num1 = Integer.parseInt(s1);
            num2 = Integer.parseInt(s2);
            num=Integer.parseInt(s);
        }
        return true;
    }

    public void avg(View v) {

        if (getNumbers()) {
            double sum = num/num1;
            t1.setText(Double.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }

    }

    public void area(View v) {
        if (getNumbers()) {
            double sum = num/num2;
            t1.setText(Double.toString(sum));
        }
        else
        {
            t1.setText("Error Please enter Required Values");
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e = (EditText) findViewById(R.id.num);
        e1 = (EditText) findViewById(R.id.num1);
        e2 = (EditText) findViewById(R.id.num3);
        final String selectedItem1 = getIntent().getStringExtra("selectedItem1");
        final String selectedItem2 = getIntent().getStringExtra("selectedItem2");
        Spinner spinner = findViewById(R.id.rentspinner);
        Spinner spinner4 = findViewById(R.id.rentspinner4);
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);
        spinner4.setOnItemSelectedListener(this);
        ArrayAdapter dats
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                date);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spinner.setAdapter(ad);
        dats.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spinner4.setAdapter(dats);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                selectedItem1 = (String) parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
        mNameSpinner = findViewById(R.id.rentspinner);
        mDateSpinner = findViewById(R.id.rentspinner4);
        mSellEditText = findViewById(R.id.num);
        mCheckEditText = findViewById(R.id.num1);
        mEnterEditText = findViewById(R.id.num3);
        mAddButton = findViewById(R.id.add);
        mViewButton = findViewById(R.id.info);
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameSpinner.getSelectedItem().toString();
                String date = mDateSpinner.getSelectedItem().toString();
                float sell = Float.parseFloat(mSellEditText.getText().toString().trim());
                int check = Integer.parseInt(mCheckEditText.getText().toString().trim());
                int enter = Integer.parseInt(mEnterEditText.getText().toString().trim());

                ContentValues values = new ContentValues();
                values.put(MyDatabaseHelper.COLUMN_NAME, name);
                values.put(MyDatabaseHelper.COLUMN_DATE, date);
                values.put(MyDatabaseHelper.COLUMN_SELL, sell);
                values.put(MyDatabaseHelper.COLUMN_CHECK, check);
                values.put(MyDatabaseHelper.COLUMN_ENTER, enter);

                mDatabase.insert(MyDatabaseHelper.TABLE_NAME, null, values);

                Toast.makeText(MainActivity.this, "Data added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }


    public void next(View view) {
        EditText editText1 = (EditText) findViewById(R.id.num);
        String text = editText1.getText().toString();

        EditText editText2 = (EditText) findViewById(R.id.num1);
        int number = Integer.parseInt(editText2.getText().toString());
        EditText editText3 = (EditText) findViewById(R.id.num3);
        int number2 = Integer.parseInt(editText2.getText().toString());

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("selectedItem1", selectedItem1);
        intent.putExtra("selectedItem2", selectedItem2);
        intent.putExtra("selectedItem3", text);
        intent.putExtra("selectedItem4", number);
        intent.putExtra("selectedItem5", number2);
        startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}