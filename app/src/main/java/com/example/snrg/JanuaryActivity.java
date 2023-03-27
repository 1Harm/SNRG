package com.example.snrg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class JanuaryActivity  extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
    String[] courses = { "Арендатор 1", "Арендатор 2",
            "Арендатор 3","Арендатор 4","Арендатор 5","Арендатор 6"};
    TextView t1,t2,t3,t4,t5,t6;
    BarChart barChart1;
    ArrayList barEntriesArrayList;
    BarDataSet barDataSet1, barDataSet2,barDataSet3,barDataSet4;
    private String category;
    private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.january_stat);
        pieChart = findViewById(R.id.pie);
        setupPieChart();
        loadPieChartData();
        Spinner spino = findViewById(R.id.spin_jan);
        spino.setOnItemSelectedListener(this);
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);
        spino.setAdapter(ad);
    }
    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(16);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(14);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(0.36f, "Одежда"));
        entries.add(new PieEntry(0.07f, "Парфюмерия"));
        entries.add(new PieEntry(0.06f, "Обувь"));
        entries.add(new PieEntry(0.51f, "Торты"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(16);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
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
                t1.setText("8199770 ТНГ");
                t2.setText("2246");
                t3.setText("Магазин парфюмерии");
                t4.setText("Зона Север, 1 этаж");
                t5.setText("728");
                t6.setText("1126342,03,75 ТНГ");
                break;
            case 1:
                t1.setText("61251904 ТНГ");
                t2.setText("39111");
                t3.setText("Торты");
                t4.setText("Цокольный этаж");
                t5.setText("21550");
                t6.setText("284231,57 ТНГ");
                break;
            case 2:
                t1.setText("7543815 ТНГ");
                t2.setText("6648");
                t3.setText("Обувь");
                t4.setText("Зона Центральная, 1 этаж");
                t5.setText("364");
                t6.setText("2072476,65 ТНГ");
                break;
            case 3:
                t1.setText("4263280 ТНГ");
                t2.setText("6696");
                t3.setText("Одежда женская");
                t4.setText("Зона Запад, 2 этаж");
                t5.setText("96");
                t6.setText("4440916,67 ТНГ");
                break;
            case 4:
                t1.setText("19515309 ТНГ");
                t2.setText("8301");
                t3.setText("Одежда");
                t4.setText("Зона Запад, 2 этаж");
                t5.setText("491");
                t6.setText("3974604,68 ТНГ");
                break;
            case 5:
                t1.setText("19369600 ТНГ");
                t2.setText("9426");
                t3.setText("Одежда женская");
                t4.setText("Зона Центральная, 1 этаж");
                t5.setText("32");
                t6.setText("60530000 ТНГ");
                break;
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void chart(View view){
        Intent intent = new Intent(JanuaryActivity.this,ChartActivity.class);
        startActivity(intent);
        finish();
    }
    public void pie(View view){
        Intent intent = new Intent(JanuaryActivity.this,PieActivity.class);
        startActivity(intent);
        finish();
    }

    public void back(View view){
        Intent intent = new Intent(JanuaryActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void getBarEntries() {
        barEntriesArrayList = new ArrayList<>();
        barEntriesArrayList.add(new BarEntry(1f, 8));
        barEntriesArrayList.add(new BarEntry(2f, 61));
        barEntriesArrayList.add(new BarEntry(3f, 7,"Ss"));
        barEntriesArrayList.add(new BarEntry(4f, 4));
        barEntriesArrayList.add(new BarEntry(5f, 20));
        barEntriesArrayList.add(new BarEntry(6f, 19));
    }
}

