package com.example.snrg;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieActivity extends AppCompatActivity {

    private int spend = 0;
    private String category;

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pie);

        pieChart = findViewById(R.id.pie);
        setupPieChart();
        loadPieChartData();
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(24);
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
        entries.add(new PieEntry(spend, category));
        entries.add(new PieEntry(0.15f, "Cafe"));
        entries.add(new PieEntry(0.80f, "Clothes"));
        entries.add(new PieEntry(0.25f, "Food"));
        entries.add(new PieEntry(0.3f, "Housing"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    public void addBurned(View v) {
        EditText burnedEditText = findViewById(R.id.burned);
        spend = Integer.parseInt(burnedEditText.getText().toString())/100;
        ArrayList<Integer> enter = new ArrayList<>();
        enter.add(spend);
        loadPieChartData();
    }

    public void addConsumed(View v) {
        EditText consumedEditText = findViewById(R.id.consumed);
        category = consumedEditText.getText().toString();
        loadPieChartData();
    }

//    private void updateChart(){
//        TextView numberOfCals = findViewById(R.id.number_of_calories);
//        numberOfCals.setText(String.valueOf(calsBurned) + " / " + calsConsumed);
//
//        ProgressBar pieChart = findViewById(R.id.stats_progressbar);
//        double d = (double) calsBurned / (double) calsConsumed;
//        int progress = (int) (d * 100);
//        pieChart.setProgress(progress);
//        pieChart.setAutofillHints("January");
//    }
}