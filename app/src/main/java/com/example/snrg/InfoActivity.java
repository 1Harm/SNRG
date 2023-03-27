package com.example.snrg;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class InfoActivity extends AppCompatActivity {


    private TextView mDataTextView;
    private TextView mAvgCheckTextView;
    private LineChart mSalesChart;
    private BarChart mComparisonChart;

    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        mDataTextView = findViewById(R.id.data_textview);
        mAvgCheckTextView = findViewById(R.id.avg_check_textview);
        mSalesChart = findViewById(R.id.sales_chart);
        mComparisonChart = findViewById(R.id.comparison_chart);

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        mDatabase = dbHelper.getReadableDatabase();

        String[] projection = {
                MyDatabaseHelper.COLUMN_NAME,
                MyDatabaseHelper.COLUMN_DATE,
                MyDatabaseHelper.COLUMN_SELL,
                MyDatabaseHelper.COLUMN_CHECK
        };

        Cursor cursor = mDatabase.query(
                MyDatabaseHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        StringBuilder data = new StringBuilder();
        ArrayList<Entry> salesEntries = new ArrayList<>();
        ArrayList<String> salesLabels = new ArrayList<>();
        ArrayList<BarEntry> comparisonEntries = new ArrayList<>();
        ArrayList<String> comparisonLabels = new ArrayList<>();

        float totalSell = 0f;
        int totalCheck = 0;

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_NAME));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_DATE));
                float sell = cursor.getFloat(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_SELL));
                int check = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_CHECK));

                data.append(name).append(" ").append(date).append(" ").append(sell).append(" ").append(check).append("\n");

                salesEntries.add(new Entry(sell, salesLabels.size()));
                salesLabels.add(name + " " + date);

                comparisonEntries.add(new BarEntry(comparisonLabels.size(), check));
                comparisonLabels.add(name + " " + date);

                totalSell += sell;
                totalCheck += check;
            } while (cursor.moveToNext());
        }

        cursor.close();

        mDataTextView.setText(data.toString());

        LineDataSet salesDataSet = new LineDataSet(salesEntries, "Sales");
        salesDataSet.setColor(Color.RED);
        salesDataSet.setValueTextColor(Color.BLACK);

        LineData salesLineData = new LineData(salesDataSet);
        mSalesChart.setData(salesLineData);

        XAxis salesXAxis = mSalesChart.getXAxis();
        salesXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        salesXAxis.setValueFormatter(new IndexAxisValueFormatter(salesLabels));
        salesXAxis.setLabelRotationAngle(-45);
        salesXAxis.setGranularity(1f);

        YAxis salesYAxisLeft = mSalesChart.getAxisLeft();
        salesYAxisLeft.setDrawGridLines(false);
        salesYAxisLeft.setAxisMinimum(0f);

        YAxis salesYAxisRight = mSalesChart.getAxisRight();
        salesYAxisRight.setEnabled(false);

        mSalesChart.getDescription().setEnabled(false);
        mSalesChart.getLegend().setEnabled(false);
        mSalesChart.animateXY(1000, 1000);

        BarDataSet comparisonDataSet = new BarDataSet(comparisonEntries, "Comparison");
        comparisonDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        comparisonDataSet.setValueTextColor(Color.BLACK);
        comparisonDataSet.setValueTextSize(12f);

        BarData comparisonBarData = new BarData(comparisonDataSet);
        mComparisonChart.setData(comparisonBarData);
        XAxis comparisonXAxis = mComparisonChart.getXAxis();
        comparisonXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        comparisonXAxis.setValueFormatter(new IndexAxisValueFormatter(comparisonLabels));
        comparisonXAxis.setLabelRotationAngle(-45);
        comparisonXAxis.setGranularity(1f);

        YAxis comparisonYAxisLeft = mComparisonChart.getAxisLeft();
        comparisonYAxisLeft.setDrawGridLines(false);
        comparisonYAxisLeft.setAxisMinimum(0f);

        YAxis comparisonYAxisRight = mComparisonChart.getAxisRight();
        comparisonYAxisRight.setEnabled(false);

        mComparisonChart.getDescription().setEnabled(false);
        mComparisonChart.getLegend().setEnabled(false);
        mComparisonChart.animateY(1000);

        float avgCheck = totalSell / totalCheck;
        mAvgCheckTextView.setText(String.valueOf(avgCheck));

        float previousAvgCheck = getPreviousAvgCheck();
        float percentChange = (avgCheck - previousAvgCheck) / previousAvgCheck * 100f;

        TextView percentChangeTextView = findViewById(R.id.percent_change_textview);

        if (percentChange >= 0) {
            percentChangeTextView.setTextColor(Color.GREEN);
        } else {
            percentChangeTextView.setTextColor(Color.RED);
        }

        percentChangeTextView.setText(String.valueOf(percentChange));
    }

    private float getPreviousAvgCheck() {
        String[] projection = {
                MyDatabaseHelper.COLUMN_SELL,
                MyDatabaseHelper.COLUMN_CHECK
        };

        Cursor cursor = mDatabase.query(
                MyDatabaseHelper.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                MyDatabaseHelper.COLUMN_ID + " DESC",
                "2"
        );

        float previousSell = 0f;
        int previousCheck = 0;

        if (cursor.moveToFirst()) {
            cursor.moveToNext();
            previousSell = cursor.getFloat(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_SELL));
            previousCheck = cursor.getInt(cursor.getColumnIndexOrThrow(MyDatabaseHelper.COLUMN_CHECK));
        }

        cursor.close();

        return previousSell / previousCheck;
    }
}