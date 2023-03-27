package com.example.snrg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DecemberActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
    String[] courses = { "Rent 1", "Арендатор 2",
            "Арендатор 3","Арендатор 4","Арендатор 5","Арендатор 6"};
    String[] course = { "1", "2","3","4","5","6"};

            TextView t1,t2,t3,t4,t5,t6;
    BarChart barChart1;
    ArrayList barEntriesArrayList;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.december_stat);
        Spinner spin = findViewById(R.id.spindec);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spin.setAdapter(ad);
        barChart = findViewById(R.id.bar);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "Продажи Арендаторов");
        barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(8f);
        barChart.getDescription().setEnabled(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(course));


        xAxis.setCenterAxisLabels(true);


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        xAxis.setGranularity(1);


        xAxis.setGranularityEnabled(true);


        barChart.setDragEnabled(true);


        barChart.setVisibleXRangeMaximum(3);


        float barSpace = 0.1f;


        barChart.getXAxis().setAxisMinimum(0);

        barChart.animate();


        barChart.invalidate();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        t1 = (TextView) findViewById(R.id.input1);
        t2 = (TextView) findViewById(R.id.textView21);
        t3 = (TextView) findViewById(R.id.textView22);
        t4 = (TextView) findViewById(R.id.textView2024);
        t5 = (TextView) findViewById(R.id.textView23);
        t6 = (TextView) findViewById(R.id.textView4);
        switch (position) {
            case 0:
                t1.setText("9484870 ТНГ");
                t2.setText("356");
                t3.setText("Магазин парфюмерии");
                t4.setText("Зона Север, 1 этаж");
                t5.setText("800");
                t6.setText("1185608,75 ТНГ");
                break;
            case 1:
                t1.setText("70028707 ТНГ");
                t2.setText("44346");
                t3.setText("Торты");
                t4.setText("Цокольный этаж");
                t5.setText("23193");
                t6.setText("301938,98 ТНГ");
                break;
            case 2:
                t1.setText("12064720 ТНГ");
                t2.setText("12613");
                t3.setText("Обувь");
                t4.setText("Зона Центральная, 1 этаж");
                t5.setText("657");
                t6.setText("1836334,86 ТНГ");
                break;
            case 3:
                t1.setText("3940902 ТНГ");
                t2.setText("7448");
                t3.setText("Одежда женская");
                t4.setText("Зона Запад, 2 этаж");
                t5.setText("97");
                t6.setText("4062785,57 ТНГ");
                break;
            case 4:
                t1.setText("19166479 ТНГ");
                t2.setText("9286");
                t3.setText("Одежда");
                t4.setText("Зона Запад, 2 этаж");
                t5.setText("469");
                t6.setText("4086669,3 ТНГ");
                break;
            case 5:
                t1.setText("18325900 ТНГ");
                t2.setText("10528");
                t3.setText("Одежда женская");
                t4.setText("Зона Центральная, 1 этаж");
                t5.setText("31");
                t6.setText("59115806,45 ТНГ");
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void chart(View view){
        Intent intent = new Intent(DecemberActivity.this,ChartActivity.class);
        startActivity(intent);
        finish();
    }
    public void pie(View view){
        Intent intent = new Intent(DecemberActivity.this,PieActivity.class);
        startActivity(intent);
        finish();
    }
    public void back(View view){
        Intent intent = new Intent(DecemberActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }



    private void getBarEntries() {
        barEntriesArrayList = new ArrayList<>();
        barEntriesArrayList.add(new BarEntry(1f, 9484870,"Арендатор1"));
        barEntriesArrayList.add(new BarEntry(2f, 70028707));
        barEntriesArrayList.add(new BarEntry(3f, 12064720));
        barEntriesArrayList.add(new BarEntry(4f, 3940902));
        barEntriesArrayList.add(new BarEntry(5f, 19166479));
        barEntriesArrayList.add(new BarEntry(6f, 18325900));
    }
}