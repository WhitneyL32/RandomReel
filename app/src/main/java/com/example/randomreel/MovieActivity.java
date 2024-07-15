package com.example.randomreel;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.randomreel.api.MovieApiService;
import com.example.randomreel.model.Movie;
import com.example.randomreel.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieActivity extends AppCompatActivity {

    private static final String API_KEY = "your_api_key"; // Replace with your actual API key
    private static final int MAX_RUNTIME = 120; // Example max runtime in minutes
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        listView = findViewById(R.id.listView);

        fetchMovies();
    }

    private void fetchMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieApiService apiService = retrofit.create(MovieApiService.class);

        Call<MovieResponse> call = apiService.discoverMovies(
                API_KEY,
                "28", // Example genre ID for Action
                5.0f, // Minimum rating
                10.0f, // Maximum rating
                "US", // Certification country
                "PG-13", // Certification
                0, // Minimum runtime
                MAX_RUNTIME // Maximum runtime
        );

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Movie> movies = response.body().getResults();
                    displayMovies(movies);
                } else {
                    Toast.makeText(MovieActivity.this, "Failed to fetch movies", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MovieActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayMovies(List<Movie> movies) {
        // Convert movie titles to a list of strings
        List<String> movieTitles = new ArrayList<>();
        for (Movie movie : movies) {
            movieTitles.add(movie.getTitle() + " (" + movie.getRuntime() + " minutes)");
        }

        // Create an ArrayAdapter and set it to the ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieTitles);
        listView.setAdapter(adapter);
    }
}
