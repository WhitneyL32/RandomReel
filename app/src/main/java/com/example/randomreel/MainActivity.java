package com.example.randomreel;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.randomreel.selections.MovieRating;
import com.example.randomreel.selections.MovieGenre;
import com.example.randomreel.selections.ViewerRatings;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMovieLengthValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup for the first spinner - Genres
        Spinner spinner1 = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MovieGenre.values());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        // Setup for the second spinner - Viewer Ratings
        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ViewerRatings.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        // Setup for the third spinner - Film Ratings
        Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MovieRating.values());
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        textViewMovieLengthValue = findViewById(R.id.textViewMovieLength);
        SeekBar seekBarMovieLength = findViewById(R.id.seekBarMovieLength);
        seekBarMovieLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int movieLength = progress + 1; // Adding 1 because range starts from 1
                // Update the TextView with the current value
                textViewMovieLengthValue.setText(movieLength + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Handle the event when the user starts to slide the seekBar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Handle the event when the user stops touching the seekBar
            }
        });
    }
}
