package com.example.randomreel;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSpinner(R.id.spinner, R.array.genre_array, R.string.genre_prompt);
        setupSpinner(R.id.spinner2, R.array.ratings_array, R.string.ratings_prompt);
        setupSpinner(R.id.spinner3, R.array.rating_category_array, R.string.rating_category_prompt);
    }

    private void setupSpinner(int spinnerId, int arrayResourceId, int promptResourceId) {
        Spinner spinner = findViewById(spinnerId);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayResourceId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setPrompt(getString(promptResourceId));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // This just gets the selected item's string
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Log the selected item
                Log.d("MainActivity", "Selected item: " + selectedItem);

                // TODO: Use the selected item to do something
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
