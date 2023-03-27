package com.example.snrg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class DataInputActivity extends AppCompatActivity {
    private EditText productNameText;
    private Spinner productCategorySpinner;
    private Button submitButton;
    String[] courses = { "Арендатор 1", "Арендатор 2",
            "Арендатор 3","Арендатор 4","Арендатор 5","Арендатор 6","Арендатор 7",
            "Арендатор 8","Арендатор 9"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_input);

        productNameText = findViewById(R.id.product_name_text);
        productCategorySpinner = findViewById(R.id.product_category_spinner);
        submitButton = findViewById(R.id.submit_button);

        // Populate the spinner with product categories
        ArrayAdapter adapter
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productCategorySpinner.setAdapter(adapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = productNameText.getText().toString();
                String productCategory = productCategorySpinner.getSelectedItem().toString();

                // Save the data to a database or file
                // ...

                // Start the dashboard activity
                Intent intent = new Intent(DataInputActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }
}