package com.example.randomreel;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.randomreel.model.Movie;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        recyclerView = findViewById(R.id.recyclerView);

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
    }
}
