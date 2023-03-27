package com.example.snrg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
    private LineChart marketAnalysisChart;
    private TextView statisticsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        marketAnalysisChart = findViewById(R.id.market_analysis_chart);
        statisticsText = findViewById(R.id.statistics_text);

        // Initialize the chart with some data
        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0f, 30f));
        entries.add(new Entry(1f, 20f));
        entries.add(new Entry(2f, 25f));
        entries.add(new Entry(3f, 35f));
        LineDataSet dataSet = new LineDataSet(entries, "Market Analysis");
        dataSet.setColor(Color.BLUE);
        dataSet.setCircleColor(Color.BLUE);
        dataSet.setLineWidth(2f);
        LineData lineData = new LineData(dataSet);
        marketAnalysisChart.setData(lineData);
        marketAnalysisChart.getDescription().setEnabled(false);
        marketAnalysisChart.getLegend().setEnabled(false);
        marketAnalysisChart.animateXY(2000, 2000);

        Intent intent = getIntent();

// Get the data from the intent
        String[] data = intent.getStringArrayExtra("data");

// Calculate some statistics
        int total = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (String item : data) {
            int value = Integer.parseInt(item);
            total += value;
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }
        int average = total / data.length;

// Display the statistics
        String statistics = "Total: " + total + "\n";
        statistics += "Average: " + average + "\n";
        statistics += "Maximum: " + max + "\n";
        statistics += "Minimum: " + min + "\n";
        statisticsText.setText(statistics);

    }
}