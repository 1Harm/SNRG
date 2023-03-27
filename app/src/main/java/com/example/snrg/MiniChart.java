package com.example.snrg;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class MiniChart extends AppCompatActivity {
    BarChart barChart1,barChart2;
    BarDataSet barDataSet1, barDataSet2,barDataSet3,barDataSet4;
    ArrayList barEntries;
    String[] category = new String[]{"December","January"};
    String[] category1 = new String[]{"Clothes","Cinema","Shop"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mini_chart);
        barChart1 = findViewById(R.id.idBarChart);

        barDataSet1 = new BarDataSet(getBarEntriesOne(), "First Renter");
        barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.purple_200));
        barDataSet2 = new BarDataSet(getBarEntriesTwo(), "Second Renter");
        barDataSet2.setColor(Color.BLUE);
        barDataSet3 = new BarDataSet(getEntriesThree(), "Third Renter");
        barDataSet3.setColor(Color.RED);
        barDataSet4 = new BarDataSet(getEntriesFour(), "Fourth Renter");
        barDataSet4.setColor(Color.YELLOW);
        BarData data1 = new BarData(barDataSet1, barDataSet2,barDataSet3,barDataSet4);
        BarData data2 = new BarData( barDataSet2,barDataSet3);

        barChart1.setData(data1);
        barChart2.setData(data2);

        barChart1.getDescription().setEnabled(true);
        XAxis xAxis = barChart1.getXAxis();
        XAxis xAxiss = barChart2.getXAxis();


        xAxis.setValueFormatter(new IndexAxisValueFormatter(category));
        xAxiss.setValueFormatter(new IndexAxisValueFormatter(category1));


        xAxis.setCenterAxisLabels(true);
        xAxiss.setCenterAxisLabels(true);


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxiss.setPosition(XAxis.XAxisPosition.BOTTOM);


        xAxis.setGranularity(1);
        xAxiss.setGranularity(1);


        xAxis.setGranularityEnabled(true);
        xAxiss.setGranularityEnabled(true);


        barChart1.setDragEnabled(true);
        barChart2.setDragEnabled(true);


        barChart1.setVisibleXRangeMaximum(3);
        barChart2.setVisibleXRangeMaximum(3);


        float barSpace = 0.1f;


        float groupSpace = 0.5f;

        data1.setBarWidth(0.15f);
        data2.setBarWidth(0.15f);


        barChart1.getXAxis().setAxisMinimum(0);
        barChart2.getXAxis().setAxisMinimum(0);

        barChart1.animate();
        barChart2.animate();

        barChart1.groupBars(0, groupSpace, barSpace);
        barChart2.groupBars(0, groupSpace, barSpace);

        barChart1.invalidate();
        barChart2.invalidate();

    }

    private ArrayList<BarEntry> getEntriesFour() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, 500));
        barEntries.add(new BarEntry(2f, 600));
        barEntries.add(new BarEntry(3f, 1900));


        return barEntries;
    }

    private ArrayList<BarEntry> getEntriesThree() {
        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, 800));
        barEntries.add(new BarEntry(2f, 600));
        barEntries.add(new BarEntry(3f, 1400));


        barEntries.stream().skip(1);
        return barEntries;
    }

    private ArrayList<BarEntry> getBarEntriesOne() {

        barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1f, 800));
        barEntries.add(new BarEntry(2f, 2000));
        barEntries.add(new BarEntry(3f, 800));
        return barEntries;
    }

    private ArrayList<BarEntry> getBarEntriesTwo() {

        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f, 2500));
        barEntries.add(new BarEntry(2f, 1200));
        barEntries.add(new BarEntry(3f, 900));
        return barEntries;
    }
}