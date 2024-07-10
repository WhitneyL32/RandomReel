package com.example.randomreel.api;

import com.example.randomreel.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {
    @GET("discover/movie")
    Call<MovieResponse> discoverMovies(
            @Query("api_key") String apiKey,
            @Query("with_genres") String genreIds,
            @Query("vote_average.gte") float minRating,
            @Query("vote_average.lte") float maxRating,
            @Query("certification_country") String certificationCountry,
            @Query("certification") String certification,
            @Query("with_runtime.gte") int minRuntime,
            @Query("with_runtime.lte") int maxRuntime
    );
}
