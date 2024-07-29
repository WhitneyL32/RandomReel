package com.example.randomreel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.randomreel.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private Button buttonReturnToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = findViewById(R.id.recyclerView);
        buttonReturnToSearch = findViewById(R.id.buttonReturnToSearch);

        // Retrieve the movie details from the intent
        ArrayList<String> movieTitles = getIntent().getStringArrayListExtra("movie_titles");
        ArrayList<String> movieOverviews = getIntent().getStringArrayListExtra("movie_overviews");
        ArrayList<Double> movieRatings = (ArrayList<Double>) getIntent().getSerializableExtra("movie_ratings");

        // Convert movie details to a list of Movie objects
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieTitles.size(); i++) {
            Movie movie = new Movie();
            movie.setTitle(movieTitles.get(i));
            movie.setOverview(movieOverviews.get(i));
            movie.setVoteAverage(movieRatings.get(i));
            movies.add(movie);
        }

        // Setup the RecyclerView with the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MovieAdapter(movies);
        recyclerView.setAdapter(adapter);

        // Set up the button to return to the main activity
        buttonReturnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: Call finish() to close the current activity
            }
        });
    }
}
