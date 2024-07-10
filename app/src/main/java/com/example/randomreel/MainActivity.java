package com.example.randomreel;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.randomreel.api.ApiClient;
import com.example.randomreel.api.MovieApiService;
import com.example.randomreel.model.MovieResponse;
import com.example.randomreel.model.Movie;
import com.example.randomreel.selections.MovieGenre;
import com.example.randomreel.selections.MovieRating;
import com.example.randomreel.selections.ViewerRatings;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMovieLengthValue;
    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup for the spinners
        Spinner spinner1 = findViewById(R.id.spinner);
        ArrayAdapter<MovieGenre> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MovieGenre.values());
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        Spinner spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<ViewerRatings> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ViewerRatings.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Spinner spinner3 = findViewById(R.id.spinner3);
        ArrayAdapter<MovieRating> adapter3 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, MovieRating.values());
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        // Setup for the seek bar and its value display
        textViewMovieLengthValue = findViewById(R.id.textViewMovieLength);
        SeekBar seekBarMovieLength = findViewById(R.id.seekBarMovieLength);
        seekBarMovieLength.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int movieLength = progress + 1; // Adding 1 because range starts from 1
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

        // Setup RecyclerView
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));

        // Add a button to trigger the API call
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(view -> {
            int selectedGenreId = ((MovieGenre) spinner1.getSelectedItem()).getId();
            float minViewerRating = ((ViewerRatings) spinner2.getSelectedItem()).getMinRating();
            float maxViewerRating = ((ViewerRatings) spinner2.getSelectedItem()).getMaxRating();
            String certification = ((MovieRating) spinner3.getSelectedItem()).getCode();
            int movieLength = seekBarMovieLength.getProgress() + 1;

            fetchMovies(selectedGenreId, minViewerRating, maxViewerRating, certification, movieLength);
        });
    }

    private void fetchMovies(int genreId, float minRating, float maxRating, String certification, int runtime) {
        String apiKey = BuildConfig.MOVIE_DB_API_KEY;
        MovieApiService apiService = ApiClient.getClient().create(MovieApiService.class);
        Call<MovieResponse> call = apiService.discoverMovies(apiKey, String.valueOf(genreId), minRating, maxRating, "US", certification, runtime, runtime);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    // Update RecyclerView with movie results
                    movieAdapter = new MovieAdapter(movies);
                    recyclerViewMovies.setAdapter(movieAdapter);
                } else {
                    Log.e("API Error", "Response code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("API Error", t.getMessage());
            }
        });
    }
}
